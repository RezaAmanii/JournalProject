package org.group12.model.Calendar;

import org.group12.model.Calendar.Event;

public interface IRecurrent {
    boolean getRecurrence();
    void setRecurrence(boolean recurrence);
    Event getParentEvent();
    void setParentEvent(Event parentEvent);
}
