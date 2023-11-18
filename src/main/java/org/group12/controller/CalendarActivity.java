package org.group12.controller;

import java.time.ZonedDateTime;

public class CalendarActivity {

    private ZonedDateTime date;
    private String clientName;

    public CalendarActivity(ZonedDateTime date, String clientName) {
        this.date = date;
        this.clientName = clientName;
    }

    public ZonedDateTime getDate() {
        return date;
    }


    public String getClientName() {
        return clientName;
    }


}
