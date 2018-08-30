package com.scorpapede.kidlog.model;

import lombok.Builder;
import lombok.Data;

import java.time.Duration;
import java.time.Instant;

@Builder
@Data
public class LogEntry {
    private String kid;
    private Instant entryTime;
    private Duration duration;
    private String activity;
}
