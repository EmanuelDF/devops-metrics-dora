package com.devops.metrics.models.tempoEntrega;

public class TempoEntregaPerformance {

    private String service;
    private String rating;

    public TempoEntregaPerformance(String service, String rating) {
        this.service = service;
        this.rating = rating;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}
