package org.group12.model.todo;

interface ITask {
    long getID();

    boolean getStatus();

    void setCompleted(boolean status);
}