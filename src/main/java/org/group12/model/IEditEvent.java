package org.group12.model;

import javafx.util.Pair;

import java.time.LocalDateTime;

public interface IEditEvent {
    void setTitle(String title);
    void setDescription(String description);
    void setTimeFrame(Pair<LocalDateTime, LocalDateTime> timeFrame);
    void setRecurrence(boolean recurrence);
    void removeTag(String tag);
    void addTag(String tag);
}
