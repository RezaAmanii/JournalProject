package org.group12.model.homeCalendar;

import org.group12.model.homeCalendar.CalendarActivity;

import java.time.ZonedDateTime;
import java.util.*;

public class HomeCalenderModel {

    // Map to store calendar activities
    public static Map<ZonedDateTime, ArrayList<CalendarActivity>> calendarActivities=new HashMap<>();
    public void saveCalender(ZonedDateTime time,CalendarActivity calendarActivity) {
        // db.connect() ;
        calendarActivities.computeIfAbsent(time, k -> new ArrayList<>()).add(calendarActivity);
        // db.close();
    }


    public  Map<Integer, List<CalendarActivity>> createCalendarMap(ZonedDateTime dateFocus) {

        List<CalendarActivity> currMonthActivities = new ArrayList<>();
        for (Map.Entry<ZonedDateTime,ArrayList<CalendarActivity>>entry:calendarActivities.entrySet()){
            if (entry.getKey().getMonth()==dateFocus.getMonth()){
                currMonthActivities.addAll(entry.getValue());
            }
        }

        Map<Integer, List<CalendarActivity>> calendarActivityMap = new HashMap<>();

        for (CalendarActivity activity : currMonthActivities) {
            int activityDate = activity.getDate().getDayOfMonth();
            if (!calendarActivityMap.containsKey(activityDate)) {
                calendarActivityMap.put(activityDate, List.of(activity));
            } else {
                List<CalendarActivity> OldListByDate = calendarActivityMap.get(activityDate);

                List<CalendarActivity> newList = new ArrayList<>(OldListByDate);
                newList.add(activity);
                calendarActivityMap.put(activityDate, newList);
            }
        }
        return calendarActivityMap;
    }


}