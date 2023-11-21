package org.group12.model.journal;

import org.group12.model.IDFactory;

import java.util.concurrent.atomic.AtomicLong;

public class JournalEntryIDFactory extends IDFactory {
    private static final String PREFIX = "JE";
    private static final AtomicLong counter = new AtomicLong(1);

    @Override
    protected String getPrefix() {
        return PREFIX;
    }

    @Override
    protected AtomicLong getCounter() {
        return counter;
    }
}

