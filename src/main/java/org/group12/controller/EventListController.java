package org.group12.controller;

import java.time.LocalDateTime;


public class EventListController implements IController {

    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;

    public EventListController(){
        this.dateFrom = LocalDateTime.now();
        this.dateTo = LocalDateTime.now().plusDays(7);
    }

    public void filterDateByEvent(){

    }

    public void getUpcommingEvent(){

    }

    public void getPastEvents(){

    }

    public void getAllEvents(){

    }
}
