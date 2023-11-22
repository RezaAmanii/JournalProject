package org.group12.controller;

import org.group12.model.Calendar.Calendar;
import org.group12.view.calendarView;


public class calendarController {

    Calendar calenderModel;
    calendarView calenderView;

    public calendarController(){
        this.calenderModel = new Calendar();
        this.calenderView = new calendarView();
        //calenderModel.addObserver(calenderView);
    }


    public void handleButtonClick(){}





}
