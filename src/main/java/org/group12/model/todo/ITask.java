package org.group12.model.todo;

import java.time.LocalDateTime;

interface ITask {
    long getID();

    String getDescription();

    LocalDateTime getDateCreated();

    LocalDateTime getDueDate();

    boolean getStatus();

    int getPriority();
}