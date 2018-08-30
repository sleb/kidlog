package com.scorpapede.kidlog.config.impl;

import com.google.common.annotations.VisibleForTesting;
import com.scorpapede.kidlog.config.Config;
import com.scorpapede.kidlog.config.ConfigException;

import java.util.Map;

public class EnvConfig implements Config {
    public static final String SKILL_ID = "SKILL_ID";
    public static final String LOG_ENTRY_TABLE_NAME = "LOG_ENTRY_TABLE_NAME";

    private String skillId;
    private String tableName;

    public EnvConfig() {
        this(System.getenv());
    }

    @VisibleForTesting
    EnvConfig(Map<String, String> env) {
        skillId = getValue(env, SKILL_ID);
        tableName = getValue(env, LOG_ENTRY_TABLE_NAME);
    }

    @Override
    public String getSkillId() {
        return skillId;
    }

    @Override
    public String getLogEntryTableName() {
        return tableName;
    }

    private String getValue(Map<String, String> env, String key) {
        String value = env.get(key);

        if (value == null) {
            throw ConfigException.missingField(key);
        }

        return value;
    }
}
