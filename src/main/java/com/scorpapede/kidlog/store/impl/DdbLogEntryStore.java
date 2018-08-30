package com.scorpapede.kidlog.store.impl;

import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.scorpapede.kidlog.model.LogEntry;
import com.scorpapede.kidlog.store.LogEntryStore;
import com.scorpapede.kidlog.store.LogEntryStoreException;
import com.scorpapede.kidlog.store.guice.LogStore;

import javax.inject.Inject;

public class DdbLogEntryStore implements LogEntryStore {
    private final Table table;

    @Inject
    public DdbLogEntryStore(@LogStore Table table) {
        this.table = table;
    }

    @Override
    public void putLogEntry(LogEntry logEntry) {

        PrimaryKey primaryKey = new PrimaryKey(
            "kid", logEntry.getKid(),
            "time", logEntry.getEntryTime().toEpochMilli()
        );

        try {
            PutItemOutcome putItemOutcome = table.putItem(
                new Item().withPrimaryKey(primaryKey)
                    .withString("duration", logEntry.getDuration().toString())
                    .withString("activity", logEntry.getActivity()));
        } catch (AmazonDynamoDBException e) {
            throw LogEntryStoreException.putFailure(e);
        }
    }
}
