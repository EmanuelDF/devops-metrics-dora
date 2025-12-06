package com.devops.metrics.gitlab;

import java.util.Optional;

public enum GitLab {
    DEFAULT;

    public Optional<String> token() {
        // Retorna um token fictício para testes
        return Optional.of("fake-token");
    }
    
}
