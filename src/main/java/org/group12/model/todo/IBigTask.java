package org.group12.model.todo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IBigTask extends ITask{
    LocalDateTime getDueDate();

    void setDueDate(LocalDateTime date);

    int getPriority();

    void setPriority(int priority);

    ArrayList<ITask> getSubTasks();
}
