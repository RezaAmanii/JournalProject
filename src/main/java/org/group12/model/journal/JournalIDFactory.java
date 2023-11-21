package org.group12.model.journal;

import org.group12.model.IDFactory;

import java.util.concurrent.atomic.AtomicLong;

public class JournalIDFactory extends IDFactory {
    private static final String PREFIX = "JRNL";
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

