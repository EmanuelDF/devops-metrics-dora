package com.devops.metrics.models.tempoEntrega;

import java.util.ArrayList;
import java.util.List;

public class TempoEntregaPerformanceDescricao {

    private String titulo;
    private List<String> regras;

    public TempoEntregaPerformanceDescricao() {
        this.titulo = "Regras de classificação do tempo de entrega";
        this.regras = new ArrayList<>();
    }

    public TempoEntregaPerformanceDescricao(String titulo, List<String> regras) {
        this.titulo = titulo;
        this.regras = regras;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getRegras() {
        return regras;
    }

    public void setRegras(List<String> regras) {
        this.regras = regras;
    }

    // Exemplo de conteúdo padrão
    public static TempoEntregaPerformanceDescricao padrao() {
        List<String> r = new ArrayList<>();
        r.add("Excelente: tempo médio de entrega inferior a 1 dia útil.");
        r.add("Bom: tempo médio de entrega entre 1 e 3 dias úteis.");
        r.add("Regular: tempo médio de entrega entre 4 e 7 dias úteis.");
        r.add("Crítico: tempo médio de entrega superior a 7 dias úteis.");
        return new TempoEntregaPerformanceDescricao(
                "Descrição e regras da performance do tempo de entrega",
                r
        );
    }

}
