import org.group12.model.Calendar.Calendar;
import org.group12.model.Calendar.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarTest {
    private Calendar calendar;
    private Event event;
    private LocalDateTime start1, start2, end1, end2;

    @BeforeEach
    public void setup() {
        calendar = new Calendar();
        start1 = LocalDateTime.of(2021, 1, 1, 2, 0);
        start2 = LocalDateTime.of(2023, 12, 11, 17, 55);
        end1 = LocalDateTime.of(2021, 1, 1, 14, 1);
        end2 = LocalDateTime.of(2024, 1, 1, 1, 2);
        event = new Event("ID", "title", "description", null, LocalDateTime.now(), null, false, null);
        calendar.addEvent(event);
        for(int i = 0; i < 10; i++) {
            calendar.addEvent("title" + i, "description" + i, null);
        }
        calendar.addEvent("matte", "jag skall kolla video", null);
    }
    @Test
    public void testMakeRecurring() {
        calendar.makeRecurring(event, 7, 14);
        // check if there is an event with the same timeframe as the original event but 7 days later
        boolean found = false;
        for (Event e : calendar.getEvents()){
            if (e.getTimeFrame().getKey().equals(event.getTimeFrame().getKey().plusDays(7))){
                found = true;
            }
        }
        assertTrue(found);
        assertTrue(event.getRecurrence());
    }

    @Test
    public void testAddEvent() {

        assertTrue(calendar.getEvents().contains(event));
//        assertTrue(calendar.getEvents().size() == 13);
        assertTrue(calendar.getEvents().get(0).getTitle().equals("title"));
    }

    // @Test
    // public void testRemoveEvent() {
    //     // calendar.addEvent(event);
    //     calendar.removeEvent(event);
    //     assertFalse(calendar.getEvents().contains(event));
    // }

    // @Test
    // public void testMakeRecurring() {
    //     calendar.addEvent(event);
    //     calendar.makeRecurring(event);
    //     assertTrue(event.getRecurrence());
    // }
}