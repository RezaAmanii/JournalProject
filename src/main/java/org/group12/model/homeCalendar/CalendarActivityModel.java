package org.group12.model.homeCalendar;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model class for calendar activities.
 */
public class CalendarActivityModel implements Serializable {

    private ZonedDateTime date;
    private String taskName;

    /**
     * Map storing the calendar activities.
     * Key: ZonedDateTime representing the date.
     * Value: ArrayList of CalendarActivityModel objects for the given date.
     */
    private static Map<ZonedDateTime, ArrayList<CalendarActivityModel>> calendarActivities = new HashMap<>();


    /**
     * Default constructor for CalendarActivityModel.
     */
    public CalendarActivityModel() {
    }

    /**
     * Returns the map of calendar activities.
     *
     * @return The map of calendar activities.
     */
    public Map<ZonedDateTime, ArrayList<CalendarActivityModel>> getCalendarActivities() {
        return this.calendarActivities;
    }

    /**
     * Constructs a CalendarActivityModel with the specified date and task name.
     *
     * @param date       The date of the calendar activity.
     * @param taskName   The name of the task for the calendar activity.
     */
    public CalendarActivityModel(ZonedDateTime date, String taskName) {
        this.date = date;
        this.taskName = taskName;
    }

    /**
     * Returns the date of the calendar activity.
     *
     * @return The date of the calendar activity.
     */
    public ZonedDateTime getDate() {
        return date;
    }

    /**
     * Sets the date of the calendar activity.
     *
     * @param date The date to set for the calendar activity.
     */
    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    /**
     * Returns the task name of the calendar activity.
     *
     * @return The task name of the calendar activity.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets the task name of the calendar activity.
     *
     * @param taskName The task name to set for the calendar activity.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Returns a string representation of the CalendarActivityModel.
     *
     * @return A string representation of the CalendarActivityModel.
     */
    @Override
    public String toString() {
        return "CalenderActivity{" +
                "date=" + date +
                ", clientName='" + taskName + '\'' +
                '}';
    }


    /**
     * Retrieves the calendar activities for the specified month.
     *
     * @param dateFocus The date to focus on for retrieving the month's activities.
     * @return A map of calendar activities grouped by day for the specified month.
     */
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
