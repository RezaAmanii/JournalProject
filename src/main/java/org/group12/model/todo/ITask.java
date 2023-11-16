package org.group12.model.todo;

import java.time.LocalDateTime;

interface ITask {
    long getID();

    String getTitle();

    String getDescription();

    LocalDateTime getDateCreated();

    LocalDateTime getDueDate();

    boolean getStatus();

    int getPriority();
}