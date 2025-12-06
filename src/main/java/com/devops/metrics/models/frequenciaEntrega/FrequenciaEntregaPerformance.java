package com.devops.metrics.models.frequenciaEntrega;

public class FrequenciaEntregaPerformance {

    private String service;
    private FrequenciaEntregaPerformanceRating rating;

    public FrequenciaEntregaPerformance(String service, FrequenciaEntregaPerformanceRating rating) {
        this.service = service;
        this.rating = rating;
    }

    public String getService() {
        return service;
    }

    public FrequenciaEntregaPerformanceRating getRating() {
        return rating;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setRating(FrequenciaEntregaPerformanceRating rating) {
        this.rating = rating;
    }

}
