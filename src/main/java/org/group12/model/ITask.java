package org.group12.model;

import java.time.LocalDateTime;

interface ITask extends INameable{
    LocalDateTime getDueDate();

    void setDueDate(LocalDateTime date);

    boolean getStatus();

    void setStatus(boolean status);

    int getPriority();

    public void setPriority(int priority);
}