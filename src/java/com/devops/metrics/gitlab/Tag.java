package com.devops.metrics.gitlab;

import java.util.stream.Stream;

public class Tag {
    private String tag;
    private String hash;

    public Tag(String tag, String hash) {
        this.tag = tag;
        this.hash = hash;
    }

    public String getTag() { return tag; }
    public String getHash() { return hash; }

    public static Stream<Tag> getTags(String service, String token) {
        return Stream.of(new Tag("v1.0", "abc123"));
    }

}
