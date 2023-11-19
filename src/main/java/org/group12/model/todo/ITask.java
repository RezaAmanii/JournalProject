package org.group12.model.todo;

import java.time.LocalDateTime;

interface ITask {
    long getID();

    LocalDateTime getDueDate();

    boolean getStatus();

    int getPriority();
}