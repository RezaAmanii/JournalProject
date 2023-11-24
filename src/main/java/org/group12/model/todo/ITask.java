package org.group12.model.todo;

import org.group12.Observers.IObservable;
import org.group12.model.IDateCreated;
import org.group12.model.INameable;

public interface ITask extends INameable, IDateCreated, IObservable {
    boolean getStatus();

    void setCompleted(boolean status);
}