package org.group12.model.homeCalendar;

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

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String toString() {
        return "CalenderActivity{" +
                "date=" + date +
                ", clientName='" + clientName + '\'' +
                '}';
    }

}
