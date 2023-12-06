package org.group12.model.homeCalendar;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarActivityModel {

    private ZonedDateTime date;
    private String taskName;

    private static Map<ZonedDateTime, ArrayList<CalendarActivityModel>> calendarActivities = new HashMap<>();


    public CalendarActivityModel() {
    }

    public Map<ZonedDateTime, ArrayList<CalendarActivityModel>> getCalendarActivities() {
        return this.calendarActivities;
    }

    public CalendarActivityModel(ZonedDateTime date, String clientName) {
        this.date = date;
        this.taskName = clientName;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return "CalenderActivity{" +
                "date=" + date +
                ", clientName='" + taskName + '\'' +
                '}';
    }


    public Map<Integer, List<CalendarActivityModel>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        List<CalendarActivityModel> currMonthActivitiesList = new ArrayList<>();
        for (Map.Entry<ZonedDateTime,ArrayList<CalendarActivityModel>>entry:calendarActivities.entrySet()){
            if (entry.getKey().getMonth()==dateFocus.getMonth()){
                currMonthActivitiesList.addAll(entry.getValue());
            }
        }

        Map<Integer, List<CalendarActivityModel>> calendarActivityMap = new HashMap<>();
        for (CalendarActivityModel activity : currMonthActivitiesList) {
            int activityDate = activity.getDate().getDayOfMonth();
            if (!calendarActivityMap.containsKey(activityDate)) {
                calendarActivityMap.put(activityDate, List.of(activity));
            } else {
                List<CalendarActivityModel> OldListByDate = calendarActivityMap.get(activityDate);

                List<CalendarActivityModel> newList = new ArrayList<>(OldListByDate);
                newList.add(activity);
                calendarActivityMap.put(activityDate, newList);
            }
        }
        return calendarActivityMap;
    }

}
