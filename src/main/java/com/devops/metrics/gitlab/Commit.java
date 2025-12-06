package com.devops.metrics.gitlab;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Stream;

public class Commit implements Comparable<Commit> {
    private String hash;
    private ZonedDateTime timestamp;
    private List<String> parentHashes;

    public Commit(String hash, ZonedDateTime timestamp, List<String> parentHashes) {
        this.hash = hash;
        this.timestamp = timestamp;
        this.parentHashes = parentHashes;
    }

    public String hash() { return hash; }
    public ZonedDateTime timestamp() { return timestamp; }
    public List<String> parentHashes() { return parentHashes; }

    public static Stream<Commit> getCommits(String service, String token) {
        return Stream.of(new Commit("abc123", ZonedDateTime.now().minusDays(2), List.of()));
    }

    @Override
    public int compareTo(Commit o) {
        return this.timestamp.compareTo(o.timestamp);
    }
    
}
