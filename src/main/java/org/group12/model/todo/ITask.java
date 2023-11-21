package org.group12.model.todo;

interface ITask {
    String getID();

    boolean getStatus();

    void setCompleted(boolean status);
}