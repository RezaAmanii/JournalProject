package org.group12.service;

import org.group12.model.homeCalendar.CalendarActivity;

import java.time.ZonedDateTime;
import java.util.*;

public class HomeCalenderService {

    // Map to store calendar activities
    public static Map<ZonedDateTime, ArrayList<CalendarActivity>> calendarActivities=new HashMap<>();
    public void saveCalender(ZonedDateTime time,CalendarActivity calendarActivity) {
        // db.connect() ;
        calendarActivities.computeIfAbsent(time, k -> new ArrayList<>()).add(calendarActivity);
        // db.close();
    }

//    public  Map<Integer, List<CalendarActivity>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
//        List<CalendarActivity> currMonthActivities = new ArrayList<>();
//        for (Map.Entry<ZonedDateTime,ArrayList<CalendarActivity>>entry:calendarActivities.entrySet()){
//            if (entry.getKey().getMonth()==dateFocus.getMonth()){
//                currMonthActivities.addAll(entry.getValue());
//            }
//        }
//        return createCalendarMap(currMonthActivities);
//    }

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


    public void deleteClaenderActivityByDate(ZonedDateTime ymd) {
//        calendarActivities.computeIfPresent(ymd, (k, v) -> {
//            v.removeIf(ca -> ca.getDate()== activityTime && Objects.equals(ca.getClientName(), calendarActivities.getClientName()));
//            if (v.isEmpty()) {
//                return null;
//            }
//            return v;
//        });
    }
}
