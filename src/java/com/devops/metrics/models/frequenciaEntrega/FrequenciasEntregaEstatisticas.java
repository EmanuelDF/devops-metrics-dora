package com.devops.metrics.models.frequenciaEntrega;

import java.util.ArrayList;
import java.util.Collection;

public class FrequenciasEntregaEstatisticas {

    private Collection<FrequenciaEntregaAgrupado> porSemana;
    private Collection<FrequenciaEntregaAgrupado> porMes;

    public FrequenciasEntregaEstatisticas() {
        this.porSemana = new ArrayList<>();
        this.porMes = new ArrayList<>();
    }

    public FrequenciasEntregaEstatisticas(Collection<FrequenciaEntregaAgrupado> porSemana,
                                          Collection<FrequenciaEntregaAgrupado> porMes) {
        this.porSemana = porSemana;
        this.porMes = porMes;
    }

    public Collection<FrequenciaEntregaAgrupado> getPorSemana() {
        return porSemana;
    }

    public void setPorSemana(Collection<FrequenciaEntregaAgrupado> porSemana) {
        this.porSemana = porSemana;
    }

    public Collection<FrequenciaEntregaAgrupado> getPorMes() {
        return porMes;
    }

    public void setPorMes(Collection<FrequenciaEntregaAgrupado> porMes) {
        this.porMes = porMes;
    }

    // Classe de apoio usada pelo serviço para representar período e quantidade de entregas
    public static class FrequenciaEntregaAgrupado {
        private int periodo;      // semana do ano (1-53) ou mês (1-12)
        private int quantidade;   // número de entregas no período

        public FrequenciaEntregaAgrupado(int periodo, int quantidade) {
            this.periodo = periodo;
            this.quantidade = quantidade;
        }

        public int getPeriodo() {
            return periodo;
        }

        public int getQuantidade() {
            return quantidade;
        }

        // Compatibilidade com código original do apêndice
        public int entregas() {
            return quantidade;
        }
    }

}
