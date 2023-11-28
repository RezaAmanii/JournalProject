package org.group12.controller;


import org.group12.model.Container;
import org.group12.view.CalendarView;
import org.group12.view.JournalView;
import org.group12.view.TaskView;

public class ControllerFactory {
    private Container model;
    private JournalView journalView;
    private CalendarView calenderView;
    private TaskView todoView;

    public  IController createController(String controllerType) {
        return switch (controllerType) {
            case "JournalController" -> new JournalController(model.getJournal(), journalView, model.getItemMap());
            case "CalendarController" -> new CalendarController(model.getCalender(), calenderView, model.getItemMap());
            case "TaskListController" -> new TaskListController();
            case "TaskController" -> new TaskController(model.getTodoCollection(),todoView, model.getItemMap());
            case "EventListController" -> new EventListController();
            default -> throw new IllegalArgumentException("Unknown controller type " + controllerType);
        };
    }
}
