import javafx.util.Pair;
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
    private Pair<LocalDateTime, LocalDateTime> timeFrame1, timeFrame2;

    @BeforeEach
    public void setup() {
        calendar = new Calendar();
        start1 = LocalDateTime.of(2021, 1, 1, 2, 0);
        start2 = LocalDateTime.of(2023, 12, 11, 17, 55);
        end1 = LocalDateTime.of(2021, 1, 1, 14, 1);
        end2 = LocalDateTime.of(2024, 1, 1, 1, 2);
        timeFrame1 = new Pair<>(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        timeFrame2 = new Pair<>(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusHours(1).plusDays(1));
        event = new Event("ID", "title", "description", timeFrame1, LocalDateTime.now(),null, false, null);
        calendar.addEvent(event);
        for(int i = 0; i < 10; i++) {
            calendar.addEvent("title" + i, "description" + i, timeFrame2);
        }
        calendar.addEvent("matte", "jag skall kolla video", timeFrame1);
    }
    @Test
    public void testReccuringAddRemove() {
        int frequency = 7;
        int duration = 22;
        calendar.makeRecurring(event, frequency, duration);
        // check if there is an event with the same timeframe as the original event but 7 days later
        int found = 0;
        for (int i = 1; i <= duration / frequency; i++) {
            LocalDateTime ev = event.getTimeFrame().getKey().plusDays(frequency * i);
            for (Event e : calendar.getEvents()) {
                if (e.getTimeFrame().getKey().equals(ev)) {
                    found += 1;
                }
            }
        }
        assertTrue(found == duration/ frequency);
        assertTrue(event.getRecurrence());

        calendar.removeRecurring(event);
        found = 0;
        for (int i = 1; i <= duration / frequency; i++) {
            LocalDateTime ev = event.getTimeFrame().getKey().plusDays(frequency * i);
            for (Event e : calendar.getEvents()) {
                if (e.getTimeFrame().getKey().equals(ev)) {
                    found += 1;
                }
            }
        }
        assertTrue(found == 0);
        assertFalse(event.getRecurrence());

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