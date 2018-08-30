package com.scorpapede.kidlog.store;

import com.google.inject.ImplementedBy;
import com.scorpapede.kidlog.model.LogEntry;
import com.scorpapede.kidlog.store.impl.DdbLogEntryStore;

@ImplementedBy(DdbLogEntryStore.class)
public interface LogEntryStore {
    void putLogEntry(LogEntry logEntry);
}
