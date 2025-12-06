package com.devops.metrics.gitlab;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Stream;

public class Deploy {
    private ZonedDateTime timestamp;
    private String user;
    private List<String> imageTag;

    public Deploy(ZonedDateTime timestamp, String user, List<String> imageTag) {
        this.timestamp = timestamp;
        this.user = user;
        this.imageTag = imageTag;
    }

    public ZonedDateTime getTimestamp() { return timestamp; }
    public String getUser() { return user; }
    public List<String> getImageTag() { return imageTag; }

    // Stub para simular deploys
    public static Stream<Deploy> getDeploys(String service, String env, String token) {
        return Stream.of(new Deploy(ZonedDateTime.now(), "ci-user", List.of("v1.0")));
    }
    
}
