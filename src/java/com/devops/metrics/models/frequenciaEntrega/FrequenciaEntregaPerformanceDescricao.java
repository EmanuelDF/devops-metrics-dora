package com.devops.metrics.models.frequenciaEntrega;

import java.util.ArrayList;
import java.util.List;

public class FrequenciaEntregaPerformanceDescricao {

    private String titulo;
    private List<String> regras;

    public FrequenciaEntregaPerformanceDescricao() {
        this.titulo = "Regras de classificação da frequência de entrega";
        this.regras = new ArrayList<>();
    }

    public FrequenciaEntregaPerformanceDescricao(String titulo, List<String> regras) {
        this.titulo = titulo;
        this.regras = regras;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<String> getRegras() {
        return regras;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setRegras(List<String> regras) {
        this.regras = regras;
    }

    // Exemplo de conteúdo padrão (alinhar com as regras descritas no serviço)
    public static FrequenciaEntregaPerformanceDescricao padrao() {
        List<String> r = new ArrayList<>();
        r.add("Diário: ao menos uma entrega em 3 dias por semana nas últimas 4 semanas.");
        r.add("Semanal: ao menos uma entrega em 8 semanas nos últimos 3 meses.");
        r.add("Mensal: ao menos uma entrega por mês em 3 meses consecutivos.");
        r.add("Trimestral: ao menos uma entrega em 3 meses.");
        r.add("Semestral: ao menos uma entrega em 6 meses.");
        r.add("Anual: ao menos uma entrega em 12 meses.");
        return new FrequenciaEntregaPerformanceDescricao(
                "Descrição e regras da performance da frequência de entrega",
                r
        );
    }

}
