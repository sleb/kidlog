package com.scorpapede.kidlog.store.guice;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.scorpapede.kidlog.config.Config;

import javax.inject.Inject;
import javax.inject.Provider;

public class LogStoreTableProvider implements Provider<Table> {
    private final Config config;
    private final DynamoDB dynamoDb;

    @Inject
    public LogStoreTableProvider(Config config, DynamoDB dynamoDb) {
        this.config = config;
        this.dynamoDb = dynamoDb;
    }

    @Override
    public Table get() {
        return dynamoDb.getTable(config.getLogEntryTableName());
    }
}
