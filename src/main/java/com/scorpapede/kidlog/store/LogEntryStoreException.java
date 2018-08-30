package com.scorpapede.kidlog.store;

public class LogEntryStoreException extends RuntimeException {
    public static LogEntryStoreException putFailure(Throwable t) {
        return new LogEntryStoreException("putLogEntry failure", t);
    }

    private LogEntryStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
