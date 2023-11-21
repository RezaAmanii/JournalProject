package org.group12.controller;

import org.group12.Listeners.TaskListListener;
import org.group12.model.todo.TaskList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskListController {

    private List<TaskList> tasksList;
    private List<TaskListListener> listeners;

    public TaskListController(){
        this.tasksList = new ArrayList<>();
        this.listeners = new ArrayList<>();
    }


    public void addTask(TaskList task){
        this.tasksList.add(task);

        // Notify the taskListListener
        for(TaskListListener listener : this.listeners){
            listener.notifyTaskAdded(task);
        }
    }

    public void removeTask(TaskList task){
        this.tasksList.remove(task);

        // Notify the taskListListener
        for(TaskListListener listener : this.listeners){
            listener.notifyTaskRemoved(task);
        }
    }


    /*
    // This method updates each attribute of the task
    public void updateTask(TaskList task, String newTitle, String newDescription, LocalDateTime newDateDue, boolean newCompeleted, int newPriority){
        task.setTitle(newTitle);
        task.setDescription(newDescription);
        task.setDueDate(newDateDue);
        task.setStatus(newCompeleted);
        task.setPriority(newPriority);

        notifyTaskUpdated(task);
    }

     */

    private void notifyTaskUpdated(TaskList task) {
        for(TaskListListener listener : this.listeners){
            listener.notifyTaskUpdated(task);
        }
    }


    /*
    // This method will filter the task list in to a list of priority tasks
    public List<TaskList> filterTaskByPeriority(int priority){
        List<TaskList> filteredListByPriority = new ArrayList<>();

        for(TaskList task : this.tasksList){
            if(task.getPriority() == priority){
                filteredListByPriority.add(task);
            }
        }
        return filteredListByPriority;
    }



    // This method will filter the task list in to a list of tasks with deadlines
    public List<TaskList> filterTaskByDeadline(LocalDateTime deadline){
        List<TaskList> filteredListByDeadline = new ArrayList<>();

        for(TaskList task : this.tasksList){
            if(task.getDueDate().equals(deadline)){
                filteredListByDeadline.add(task);
            }
        }
        return filteredListByDeadline;
    }

     */

    // This method gets specific task by its ID (Maybe we should Implement binary search)
    /*public TaskList getTask(Long taskID){
        for(TaskList task : this.tasksList){
            if(task.getID() == taskID){
                return task;
            }
        }
        return null;
    }*/

    /*
    // This method gets list of all completed tasks
    public List<TaskList> getCompletedTask(){
        List<TaskList> compeletedTasks = new ArrayList<>();

        for(TaskList task : this.tasksList){
            if(task.getStatus() == true){
                compeletedTasks.add(task);
            }
        }
        return compeletedTasks;
    }

     */

    // Note: As you can see some of these methods have a similar body and maybe by using Template pattern we can make it more OOP

}
