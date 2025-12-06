package com.devops.metrics.models.tempoEntrega;

import java.math.BigDecimal;

public class TempoEntregaEstatiscasGroup {

    private int quantidadeNameSpaces;
    private int quantidadeNameSpacesDeploy;
    private long deploys;
    private BigDecimal media;
    private BigDecimal mediana;
    private BigDecimal desvioPadrao;
    private BigDecimal mediaDeploysDiarios;

    public TempoEntregaEstatiscasGroup() {}

    public TempoEntregaEstatiscasGroup(int quantidadeNameSpaces, int quantidadeNameSpacesDeploy,
                                       long deploys, BigDecimal media, BigDecimal mediana,
                                       BigDecimal desvioPadrao, BigDecimal mediaDeploysDiarios) {
        this.quantidadeNameSpaces = quantidadeNameSpaces;
        this.quantidadeNameSpacesDeploy = quantidadeNameSpacesDeploy;
        this.deploys = deploys;
        this.media = media;
        this.mediana = mediana;
        this.desvioPadrao = desvioPadrao;
        this.mediaDeploysDiarios = mediaDeploysDiarios;
    }

    public int getQuantidadeNameSpaces() { return quantidadeNameSpaces; }
    public void setQuantidadeNameSpaces(int quantidadeNameSpaces) { this.quantidadeNameSpaces = quantidadeNameSpaces; }

    public int getQuantidadeNameSpacesDeploy() { return quantidadeNameSpacesDeploy; }
    public void setQuantidadeNameSpacesDeploy(int quantidadeNameSpacesDeploy) { this.quantidadeNameSpacesDeploy = quantidadeNameSpacesDeploy; }

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
    
}
