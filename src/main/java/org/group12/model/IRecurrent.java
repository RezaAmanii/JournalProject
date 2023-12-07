package org.group12.model;

import org.group12.model.Calendar.Event;

public interface IRecurrent {
    boolean getRecurrence();
    Event getParentEvent();
}
