package com.devops.metrics.service;

import com.devops.metrics.dao.MsDao;
import com.devops.metrics.exceptions.MetricsException;
import com.devops.metrics.gitlab.*;
import com.devops.metrics.k8s.Cluster;
import com.devops.metrics.k8s.Cluster.Environment;
import com.devops.metrics.k8s.ReplicaSet;
import com.devops.metrics.models.tempoEntrega.TempoEntregaEstatisticas;
import com.devops.metrics.models.tempoEntrega.TempoEntregaEstatiscasGroup;
import com.devops.metrics.models.tempoEntrega.TempoEntregaEstatisticas.FrequenciaAcumulada;
import com.devops.metrics.models.tempoEntrega.TempoEntregaEstatisticas.FrequenciaNamespace;
import com.devops.metrics.util.Holiday;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import io.quarkus.cache.CacheResult;
import org.eclipse.microprofile.opentracing.Traced;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.time.YearMonth;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Comparator.comparing;
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;
import static java.util.stream.Collectors.*;

@ApplicationScoped
@Traced
public class TempoEntregaService {

    @Inject
    MsDao apps;

    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    public static final String INFORME_NAMESPACE = "Informe um namespace.";
    public static final String INFORME_TOKEN = "Informe o token de acesso.";
    public static final String INFORME_APP = "Informe uma app válida.";
    private static final Month START_MONTH = Month.SEPTEMBER;
    private static final Month END_MONTH = Month.DECEMBER;

    private static final ZonedDateTime START = YearMonth.of(2022, START_MONTH).atDay(1)
            .atStartOfDay(ZoneId.of("-3"));
    private static final ZonedDateTime END = YearMonth.of(2022, END_MONTH).atEndOfMonth()
            .atTime(LocalTime.MAX).atZone(ZoneId.of("-3"));
    public static final String DATA_INVALIDA = "Data final maior que a data inicial.";

    private Optional<ZonedDateTime> convertData(String data, boolean atStartDay) {
        if (data.isBlank() || !data.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
            return Optional.empty();
        }
        if (atStartDay) {
            return Optional.of(LocalDate.parse(data, Holiday.DT_FMT_YMD).atStartOfDay()
                    .atZone(ZoneId.systemDefault()));
        }
        return Optional.of(LocalDate.parse(data, Holiday.DT_FMT_YMD).atTime(LocalTime.MAX)
                .atZone(ZoneId.systemDefault()));
    }

    public TempoEntregaEstatisticas criarEstatisticasTempoEntrega(String service, String token,
                                                                  String dataInicio, String dataFim) {
        var zdtInicio = convertData(dataInicio, true).orElse(START);
        var zdtFim = convertData(dataFim, false).orElse(END);
        LOG.log(Level.INFO, "Data Inicio: " + zdtInicio + " Data Fim: " + zdtFim);

        if (zdtFim.isBefore(zdtInicio)) {
            throw new IllegalArgumentException(DATA_INVALIDA);
        }

        if (token.isBlank()) {
            token = GitLab.valueOf(GitLabUtil.returnGitService(service)).token().get();
        }

        if (service.isBlank()) {
            throw new IllegalArgumentException(INFORME_NAMESPACE);
        }

        return runDeployed(List.of(service), token, zdtInicio, zdtFim);
    }

    @CacheResult(cacheName = "consultaNamespaces")
    public Collection<String> namespacesFromCluster(Environment clusterEnv) {
        var clusters = Cluster.of(clusterEnv);
        return clusters.parallel()
                .flatMap(ReplicaSet::of)
                .map(ReplicaSet::namespace)
                .distinct()
                .filter(ns -> !ns.startsWith("des") && !ns.startsWith("hml"))
                .filter(ns -> ns.matches("[a-zA-Z][a-zA-Z0-9]{2}-.+"))
                .sorted()
                .collect(toList());
    }

    @CacheResult(cacheName = "estatisticasTempoEntrega")
    public TempoEntregaEstatisticas criarEstatisticasGeral(String dataInicio, String dataFim) {
        var inicio = convertData(dataInicio, true).orElse(START);
        var fim = convertData(dataFim, false).orElse(END);
        var token = GitLab.DEFAULT.token().get();
        return runDeployed(namespacesFromCluster(Environment.PRD), token, inicio, fim);
    }

    @CacheResult(cacheName = "estatisticasTempoEntregaSigla")
    public Map<String, TempoEntregaEstatiscasGroup> criarEstatisticaMs(String dataInicio, String dataFim) {
        var inicio = convertData(dataInicio, true).orElse(START);
        var fim = convertData(dataFim, false).orElse(END);
        var namespacesDeployados = namespacesFromCluster(Environment.PRD);
        var apps = listaMs(namespacesDeployados);
        var token = GitLab.DEFAULT.token().get();
        return runDeployedGroup(apps, namespacesDeployados, "", inicio, fim);
    }

    @CacheResult(cacheName = "estatisticasTempoEntregaSiglaEspecifica")
    public Map<String, TempoEntregaEstatiscasGroup> criarEstatisticaSiglaUnica(String app, String dataInicio, String dataFim) throws MetricsException {
        Collection<String> namespacesDeployados = new ArrayList<>();
        List<String> appsList = new ArrayList<>();
        if (app.matches("^[a-zA-Z]{3}$")) {
            appsList.add(app.toLowerCase(Locale.ROOT));
            namespacesDeployados = namespacesFromCluster(Environment.PRD).stream()
                    .filter(s -> s.startsWith(app.toLowerCase(Locale.ROOT)))
                    .collect(toList());
        } else {
            throw new MetricsException("001", INFORME_APP);
        }
        var inicio = convertData(dataInicio, true).orElse(START);
        var fim = convertData(dataFim, false).orElse(END);
        return runDeployedGroup(appsList, namespacesDeployados, "", inicio, fim);
    }

}
