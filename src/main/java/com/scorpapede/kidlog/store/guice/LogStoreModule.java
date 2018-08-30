package com.scorpapede.kidlog.store.guice;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.google.inject.AbstractModule;

public class LogStoreModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DynamoDB.class).toProvider(DynamoDbProvider.class);
        bind(Table.class).annotatedWith(LogStore.class).toProvider(LogStoreTableProvider.class);
    }
}
