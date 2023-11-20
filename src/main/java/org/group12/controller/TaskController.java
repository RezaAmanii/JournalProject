package org.group12.controller;

import org.group12.Listeners.TaskListener;
import org.group12.model.todo.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskController {

    private List<TaskListener> listeners = new ArrayList<>();

    // List over all subtask A task contains
    private List<Task> subTasks = new ArrayList<>();


    private void addSubtask(Task subTask){
        subTasks.add(subTask);

        // Notify the listener that a subtask is added
        for(TaskListener listener : listeners){
            listener.notifySubTaskAdded(subTask);
        }
    }

    private void removeSubTask(Task subTask){
        subTasks.remove(subTask);

        // Notify the listener that a subtask is removed
        for(TaskListener listener : listeners){
            listener.notifySubTaskRemoved(subTask);
        }
    }

    private void markSubTaskAsDone(Task subTask){
        if(!subTask.getStatus()){
            subTask.setCompleted(true);
        }

        // Notify the listeners that a task is completed
        for(TaskListener listener : listeners){
            listener.notifySubTaskCompleted(subTask);
        }
    }

    private void markSubTaskAsNotDone(Task subTask){
        if(subTask.getStatus()){
            subTask.setCompleted(false);
        }

        // Notify the listeners that a task is not completed
        for(TaskListener listener : listeners){
            listener.notifySubTaskNotCompeleted(subTask);
        }
    }

    private void renameSubTask(Task subTask, String newTaskName){
        subTask.setTitle(newTaskName);
    }

    /*private String getSubTaskDetails(Task subTask){
        return subTask.getDescription();
    }

     */
}
