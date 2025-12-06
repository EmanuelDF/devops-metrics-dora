package com.devops.metrics.k8s;

import java.util.stream.Stream;

public class Cluster {

    public enum Environment {
        PRD, HML, DES
    }

    private Environment env;

    public Cluster(Environment env) {
        this.env = env;
    }

    public static Stream<Cluster> of(Environment env) {
        return Stream.of(new Cluster(env));
    }

    public Environment getEnv() { return env; }
    
}
