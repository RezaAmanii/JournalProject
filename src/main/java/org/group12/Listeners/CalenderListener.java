package org.group12.Listeners;

import org.group12.model.Calendar.Event;

public interface CalenderListener extends PlanITListener{
    void notifyCalenderEventAdded(Event event);
    void notifyCalenderEventUpdated(Event event);
    void notifyCalenderEventRemoved(Event event);
}
