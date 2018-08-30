package com.scorpapede.kidlog.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.slu.entityresolution.Resolution;
import com.amazon.ask.model.slu.entityresolution.Value;
import com.amazon.ask.model.slu.entityresolution.ValueWrapper;
import com.amazon.ask.response.ResponseBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.*;
import com.scorpapede.kidlog.config.impl.EnvConfig;
import com.scorpapede.kidlog.model.LogEntry;
import com.scorpapede.kidlog.store.LogEntryStore;
import com.scorpapede.kidlog.store.LogEntryStoreException;
import com.scorpapede.kidlog.util.Prompts;

import javax.inject.Inject;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.scorpapede.kidlog.util.Predicates.erSuccess;
import static com.scorpapede.kidlog.util.Prompts.FAILED_TO_STORE;
import static com.scorpapede.kidlog.util.Prompts.GOT_IT;
import static java.lang.String.format;

public class LogActivityHandler implements RequestHandler {

    private final LogEntryStore entryStore;

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("LogActivityIntent"));
    }

    @Inject
    public LogActivityHandler(LogEntryStore entryStore) {
        this.entryStore = entryStore;
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Map<String, Slot> slots = ((IntentRequest) input.getRequestEnvelope().getRequest()).getIntent().getSlots();
        Slot kidSlot = slots.get("kid");
        Slot durationSlot = slots.get("duration");
        Slot activitySlot = slots.get("activity");

        LogEntry logEntry = LogEntry.builder()
            .duration(Duration.parse(durationSlot.getValue()))
            .entryTime(Instant.now())
            .kid(kidSlot.getValue())
            .activity(getEntityValue(activitySlot).getId())
            .build();

        try {
            entryStore.putLogEntry(logEntry);
        } catch (LogEntryStoreException e) {
            return new ResponseBuilder().withSpeech(FAILED_TO_STORE).build();
        }

        return new ResponseBuilder().withSpeech(GOT_IT).build();
    }

    private Value getEntityValue(Slot slot) {
        Optional<Value> value = slot.getResolutions()
            .getResolutionsPerAuthority()
            .stream()
            .filter(erSuccess())
            .findFirst()
            .map(Resolution::getValues)
            .orElse(Collections.emptyList())
            .stream()
            .findFirst()
            .map(ValueWrapper::getValue);
        return value.orElseThrow(() -> new RuntimeException(format("Missing slot: '%s'", slot.getName())));
    }
}
