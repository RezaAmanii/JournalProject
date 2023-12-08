package org.group12.controller;


import org.group12.model.Container;
import org.group12.view.CalendarView;
import org.group12.view.JournalView;
import org.group12.view.TaskView;
import org.group12.model.Items;

public class ControllerFactory {
    private Container model;
    private JournalView journalView;
    private CalendarView calenderView;
    private TaskView todoView;
    private Items itemMap;

    public  IController createController(String controllerType, Items itemMap) {
        return switch (controllerType) {
            case "JournalController" -> new JournalController();
            case "CalendarController" -> new CalendarController();
            case "TaskListController" -> TaskListController.getInstance();
            case "TaskController" -> new TaskController(model.getTodoCollection(),todoView, itemMap);
            case "EventListController" -> new EventListController();
            default -> throw new IllegalArgumentException("Unknown controller type " + controllerType);
        };
    }
}
