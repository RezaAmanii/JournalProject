package org.group12.model.Calendar.interfaces;

import org.group12.model.Calendar.Event;

public interface IRecurrent {
    boolean getRecurrence();
    void setRecurrence(boolean recurrence);
    IEvent getParentEvent();
    void setParentEvent(Event parentEvent);
}
