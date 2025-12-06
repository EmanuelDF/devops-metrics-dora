package com.devops.metrics.service;

import com.devops.metrics.models.frequenciaEntrega.FrequenciaEntregaPerformance;
import com.devops.metrics.models.frequenciaEntrega.FrequenciaEntregaPerformanceRating;
import com.devops.metrics.models.frequenciaEntrega.FrequenciasEntregaEstatisticas;
import com.devops.metrics.models.frequenciaEntrega.FrequenciasEntregaEstatisticas.FrequenciaEntregaAgrupado;

import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.invoke.MethodHandles;
import java.util.Collection;
import java.util.logging.Logger;

@ApplicationScoped
@Traced
public class FrequenciaEntregaPerformanceService {

    @Inject
    FrequenciaEntregaService frequenciaEntregaService;

    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    public FrequenciaEntregaPerformance calculoFrequenciaEntregaPerformance(String service, String token) {
        try {
            final FrequenciasEntregaEstatisticas frequenciaEntrega = frequenciaEntregaService.calculoFrequenciaEntrega(service, token);
            final Collection<FrequenciaEntregaAgrupado> frequenciaEntregaSemana = frequenciaEntrega.getPorSemana();
            final Collection<FrequenciaEntregaAgrupado> frequenciaEntregaMensal = frequenciaEntrega.getPorMes();

            if (frequenciaEntregaSemana != null && verificarPerformanceDiaria(frequenciaEntregaSemana)) {
                return new FrequenciaEntregaPerformance(service, FrequenciaEntregaPerformanceRating.DIARIO);
            } else if (frequenciaEntregaMensal != null && verificarPerformanceMensal(frequenciaEntregaMensal)) {
                return new FrequenciaEntregaPerformance(service, FrequenciaEntregaPerformanceRating.MENSAL);
            }
            return new FrequenciaEntregaPerformance(service, FrequenciaEntregaPerformanceRating.SEMESTRAL);
        } catch (Exception e) {
            LOG.severe("Erro ao calcular performance: " + e.getMessage());
            return new FrequenciaEntregaPerformance(service, FrequenciaEntregaPerformanceRating.INDEFINIDO);
        }
    }

    private boolean verificarPerformanceDiaria(Collection<FrequenciaEntregaAgrupado> dados) {
        return dados.stream().anyMatch(d -> d.getQuantidade() > 5);
    }

    private boolean verificarPerformanceMensal(Collection<FrequenciaEntregaAgrupado> dados) {
        return dados.stream().anyMatch(d -> d.getQuantidade() > 20);
    }

}
