package com.scorpapede.kidlog.config.impl;

import com.google.common.collect.ImmutableMap;
import com.scorpapede.kidlog.config.ConfigException;
import org.junit.jupiter.api.Test;

import static com.scorpapede.kidlog.config.impl.EnvConfig.LOG_ENTRY_TABLE_NAME;
import static com.scorpapede.kidlog.config.impl.EnvConfig.SKILL_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnvConfigTest {

    @Test
    void testCompleteConfig() {
        String expectedAppId = "the app ID";
        String expectedTableName = "the table name";
        EnvConfig config = new EnvConfig(ImmutableMap.of(
            SKILL_ID, expectedAppId,
            LOG_ENTRY_TABLE_NAME, expectedTableName
        ));

        assertEquals(expectedAppId, config.getSkillId());
    }

    @Test
    void testMissingAppId() {
        assertThrows(ConfigException.class, () -> {
            new EnvConfig(ImmutableMap.of(LOG_ENTRY_TABLE_NAME, "whatever"));
        });
    }

    @Test
    void testMissingTableName() {
        assertThrows(ConfigException.class, () -> {
            new EnvConfig(ImmutableMap.of(SKILL_ID, "whatever"));
        });
    }
}