package org.group12.controller;


public class ControllerFactory {

    public static IController createController(String controllerType) {
        switch (controllerType) {
//            case "JournalController":
//                return new JournalController();
            case "CalendarController":
                return new CalendarController();
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
