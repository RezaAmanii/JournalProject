package org.group12.model.todo;

import java.time.LocalDateTime;

interface ITask {
    long getID();

    String getDescription();

    LocalDateTime getDueDate();

    boolean getStatus();

    int getPriority();
}