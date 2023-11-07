package org.group12.model;

import java.time.LocalDateTime;

public interface ITask {
    String getTitle();

    void setTitle(String title);

    String getDescription();

    void setDescription(String desc);

    LocalDateTime getDateCreated();

    LocalDateTime getDueDate();

    void setDueDate(LocalDateTime date);

    boolean getStatus();

    void setStatus(boolean status);

    int getPriority();

    public void setPriority(int priority);
}