package org.group12.model;

import java.time.LocalDateTime;

interface ITaskItem {
    long getID();

    String getTitle();

    String getDescription();

    LocalDateTime getDateCreated();

    LocalDateTime getDueDate();

    boolean getStatus();

    int getPriority();
}