package org.group12.controller;


import org.group12.model.Container;
import org.group12.view.JournalView;

public class ControllerFactory {
    private Container model;
    private JournalView journalView;

    public  IController createController(String controllerType) {
        return switch (controllerType) {
            case "JournalController" -> new JournalController(model.getJournal(), journalView, model.getItemMap());
            case "CalendarController" -> new CalendarController(model.getCalender(), model.getItemMap());
            case "TaskListController" -> new TaskListController();
            case "TaskController" -> new TaskController();
            case "EventListController" -> new EventListController();
            default -> throw new IllegalArgumentException("Unknown controller type " + controllerType);
        };
    }
}
