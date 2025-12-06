package com.devops.metrics.models.tempoEntrega;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public class TempoEntregaEstatisticas {

    private List<String> namespaces;
    private int quantidadeNamespaces;
    private int quantidadeNamespacesDeploy;
    private long deploys;
    private BigDecimal media;
    private BigDecimal mediana;
    private BigDecimal desvioPadrao;
    private BigDecimal mediaDeploysDiarios;
    private ZonedDateTime inicio;
    private ZonedDateTime fim;

    // Getters e Setters
    public List<String> getNamespaces() { return namespaces; }
    public void setNamespaces(List<String> namespaces) { this.namespaces = namespaces; }

    public int getQuantidadeNamespaces() { return quantidadeNamespaces; }
    public void setQuantidadeNamespaces(int quantidadeNamespaces) { this.quantidadeNamespaces = quantidadeNamespaces; }

    public int getQuantidadeNamespacesDeploy() { return quantidadeNamespacesDeploy; }
    public void setQuantidadeNamespacesDeploy(int quantidadeNamespacesDeploy) { this.quantidadeNamespacesDeploy = quantidadeNamespacesDeploy; }

    public long getDeploys() { return deploys; }
    public void setDeploys(long deploys) { this.deploys = deploys; }

    public BigDecimal getMedia() { return media; }
    public void setMedia(BigDecimal media) { this.media = media; }

    public BigDecimal getMediana() { return mediana; }
    public void setMediana(BigDecimal mediana) { this.mediana = mediana; }

    public BigDecimal getDesvioPadrao() { return desvioPadrao; }
    public void setDesvioPadrao(BigDecimal desvioPadrao) { this.desvioPadrao = desvioPadrao; }

    public BigDecimal getMediaDeploysDiarios() { return mediaDeploysDiarios; }
    public void setMediaDeploysDiarios(BigDecimal mediaDeploysDiarios) { this.mediaDeploysDiarios = mediaDeploysDiarios; }

    public ZonedDateTime getInicio() { return inicio; }
    public void setInicio(ZonedDateTime inicio) { this.inicio = inicio; }

    public ZonedDateTime getFim() { return fim; }
    public void setFim(ZonedDateTime fim) { this.fim = fim; }

    // Classes auxiliares internas
    public static class FrequenciaAcumulada {
        private int dias;
        private long deploys;
        private BigDecimal porcentagem;

        public FrequenciaAcumulada(int dias, long deploys, BigDecimal porcentagem) {
            this.dias = dias;
            this.deploys = deploys;
            this.porcentagem = porcentagem;
        }

        public int getDias() { return dias; }
        public long getDeploys() { return deploys; }
        public BigDecimal getPorcentagem() { return porcentagem; }
    }

    public static class FrequenciaNamespace {
        private String namespace;
        private List<Integer> frequencia;

        public FrequenciaNamespace(String namespace, List<Integer> frequencia) {
            this.namespace = namespace;
            this.frequencia = frequencia;
        }

        public String getNamespace() { return namespace; }
        public List<Integer> getFrequencia() { return frequencia; }
    }

}
