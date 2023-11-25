package org.group12.controller;


import org.group12.model.Container;

public class ControllerFactory {
    private Container model;

    public  IController createController(String controllerType) {
        switch (controllerType) {
            case "JournalController":
                return new JournalController(model.getJournal(), model.getItemMap());
            case "CalendarController":
                return new CalendarController(model.getCalender(), model.getItemMap());
            case "TaskListController":
                return new TaskListController();
            case "TaskController":
                return new TaskController();
            case "EventListController":
                return new EventListController();
            default:
                return null;
        }
    }
}
