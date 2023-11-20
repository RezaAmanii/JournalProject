package org.group12.Listeners;

import org.group12.model.todo.Task;

public interface TaskListener {

    void notifySubTaskAdded(Task task);
    void notifySubTaskRemoved(Task task);
    void notifySubTaskCompleted(Task task);
    void notifySubTaskNotCompeleted(Task task);

}
