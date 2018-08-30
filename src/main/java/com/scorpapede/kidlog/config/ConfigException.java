package com.scorpapede.kidlog.config;

import static java.lang.String.format;

public class ConfigException extends RuntimeException {
    public static ConfigException missingField(String field) {
        return new ConfigException(format("missing field '%s'", field));
    }

    private ConfigException(String message) {
        super(message);
    }
}
