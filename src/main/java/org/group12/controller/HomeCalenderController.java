package org.group12.controller;

import org.group12.model.homeCalendar.CalendarActivity;
import org.group12.model.homeCalendar.HomeCalenderModel;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeCalenderController {

    public CalendarActivity calendarActivity ;
    public static HomeCalenderModel homeCalenderService = new HomeCalenderModel();


    public void save(ZonedDateTime time,CalendarActivity calendarActivity) {
        homeCalenderService.saveCalender(time,calendarActivity);
    }

    public Map<ZonedDateTime, ArrayList<CalendarActivity>>  getMainClaenderActivitiesMap() {
        return  homeCalenderService.calendarActivities;
    }

    public List<CalendarActivity>  getCalendarActivitiesMonth(ZonedDateTime dateFocus, int selectedDay) {
        return  homeCalenderService.createCalendarMap(dateFocus).get(selectedDay);
    }

    public Map<Integer, List<CalendarActivity>>  getCalendarActivitiesMonthMap(ZonedDateTime dateFocus) {
        return  homeCalenderService.createCalendarMap(dateFocus);
    }


}
