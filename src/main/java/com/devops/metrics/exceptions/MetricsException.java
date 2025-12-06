package com.devops.metrics.exceptions;

public class MetricsException extends Exception {
    private final String code;

    public MetricsException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
