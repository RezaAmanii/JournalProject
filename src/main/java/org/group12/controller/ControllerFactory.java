package org.group12.controller;


import org.group12.model.Container;
import org.group12.view.CalendarView;
import org.group12.view.HomeCalendarView;
import org.group12.view.JournalView;
import org.group12.view.TaskView;
import org.group12.model.Items;

public class ControllerFactory {
    private Container model;

    private HomeCalendarView homeCalendarView;
    private JournalView journalView;
    private CalendarView calenderView;
    private TaskView todoView;
    private Items itemMap;

    public  IController createController(String controllerType, Items itemMap) {
        return switch (controllerType) {
            case "JournalController" -> new JournalController(model.getJournal(), journalView, itemMap);
            case "CalendarController" -> new CalendarController(model.getCalender(), calenderView, itemMap);
            case "TaskListController" -> new TaskListController();
            case "TaskController" -> new TaskController(model.getTodoCollection(),todoView, itemMap);
            case "EventListController" -> new EventListController();
            default -> throw new IllegalArgumentException("Unknown controller type " + controllerType);
        };
    }
}
