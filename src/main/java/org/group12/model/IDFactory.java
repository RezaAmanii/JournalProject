package org.group12.model;

public class IDFactory {
    private long ID = 0;
    public long generateUniqueID() {
        ID++;
        return ID;
    }
}