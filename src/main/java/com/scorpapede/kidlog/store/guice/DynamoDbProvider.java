package com.scorpapede.kidlog.store.guice;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

import javax.inject.Provider;

public class DynamoDbProvider implements Provider<DynamoDB> {
    @Override
    public DynamoDB get() {
        return new DynamoDB(AmazonDynamoDBAsyncClientBuilder.defaultClient());
    }
}
