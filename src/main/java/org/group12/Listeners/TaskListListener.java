package org.group12.Listeners;

import org.group12.model.TaskList;

public interface TaskListListener extends PlanITListener{
    void notifyTaskAdded(TaskList task);
    void notifyTaskUpdated(TaskList task);
    void notifyTaskRemoved(TaskList task);
}
