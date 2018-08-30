package com.scorpapede.kidlog.config;

import com.google.inject.ImplementedBy;
import com.scorpapede.kidlog.config.impl.EnvConfig;

@ImplementedBy(EnvConfig.class)
public interface Config {
    String getSkillId();
    String getLogEntryTableName();
}
