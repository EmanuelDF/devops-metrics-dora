package com.devops.metrics.k8s;

import java.util.stream.Stream;

public class ReplicaSet {
    private String namespace;

    public ReplicaSet(String namespace) {
        this.namespace = namespace;
    }

    public String namespace() { return namespace; }

    public static Stream<ReplicaSet> of(Cluster cluster) {
        // Simula namespaces
        return Stream.of(new ReplicaSet("app1-prd"), new ReplicaSet("app2-prd"));
    }
    
}
