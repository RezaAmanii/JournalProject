import javafx.util.Pair;
import org.group12.model.Calendar.Calendar;
import org.group12.model.Calendar.Event;
import org.group12.model.Calendar.interfaces.IEvent;
import org.group12.model.Items;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalendarTest {

   private Calendar calendar;

    private IEvent event;
    private LocalDateTime start1, start2, end1, end2;
    private Pair<LocalDateTime, LocalDateTime> timeFrame1, timeFrame2;

    @BeforeEach
    public void setup() {
        calendar = new Calendar(Items.getInstance());
        start1 = LocalDateTime.of(2021, 1, 1, 2, 0);
        end1 = LocalDateTime.of(2021, 1, 1, 14, 1);
        timeFrame1 = new Pair<>(start1, end1);

        start2 = LocalDateTime.of(2024, 12, 24, 17, 55);
        end2 = LocalDateTime.of(2025, 1, 1, 1, 2);
        timeFrame2 = new Pair<>(start2, end2);


        calendar.addEvent("title", "description", timeFrame1);
        event = calendar.getEvents().get(0);
        for(int i = 0; i < 10; i++) {
            calendar.addEvent("title" + i, "description" + i, timeFrame2);
        }
        calendar.addEvent("matte", "jag skall kolla video", timeFrame1);
    }


    @Test
    public void testRecurring() {
        int frequency = 7;
        int duration = 22;
        calendar.makeRecurring(event, frequency, duration); // should add 22/7 = 3 events
        // check if there is an event with the same timeframe as the original event but 7 days later
        int found = 0;
        for (int i = 1; i <= duration / frequency; i++) {
            LocalDateTime ev = event.getTimeFrame().getKey().plusDays(frequency * i);
            for (IEvent e : calendar.getEvents()) {
                if (e.getTimeFrame().getKey().equals(ev)) {
                    found += 1;
                }
            }
        }
        assertEquals(found, duration / frequency);
        assertTrue(event.getRecurrence());
        assertEquals(calendar.getEvents().size(), 12 + duration / frequency);

        IEvent aRecuringEvent = calendar.getEvents().get(14); // picks one of the newly created events fron recurring
        calendar.detachRecurring(aRecuringEvent);
        assertFalse(aRecuringEvent.getRecurrence());

        calendar.removeRecurring(event);
        found = 0;
        for (int i = 1; i <= duration / frequency; i++) {
            LocalDateTime ev = event.getTimeFrame().getKey().plusDays(frequency * i);
            for (IEvent e : calendar.getEvents()) {
                if (e.getTimeFrame().getKey().equals(ev)) {
                    found += 1;
                }
            }
        }
        assertEquals(1, found); // only the original event should be left and the one detached
        assertFalse(event.getRecurrence());
        assertEquals(calendar.getEvents().size(), 13); // 1 original + 1 detached + 10 + 1

    }

    @Test
    public void testDetachRecurring() {

    }

    @Test
    public void testAddRemoveEvent() {
        assertTrue(calendar.getEvents().contains(event));
        assertEquals(12, calendar.getEvents().size());
        assertEquals("title", calendar.getEvents().get(0).getTitle());
        assertEquals("description", calendar.getEvents().get(0).getDescription());
        assertEquals(timeFrame1, calendar.getEvents().get(0).getTimeFrame());

        calendar.addEvent("toberemoved", "removeme", timeFrame2);
        IEvent tempevent = calendar.getEvents().get(12);
        assertEquals("toberemoved", tempevent.getTitle());
        assertTrue(calendar.getEvents().contains(tempevent));
        assertEquals(13, calendar.getEvents().size());
        calendar.removeEvent(tempevent);
        assertFalse(calendar.getEvents().contains(tempevent));
        assertEquals(12, calendar.getEvents().size());
//        assertEquals("EV25", calendar.getEvents().get(0).getID()); Dosent work because of the ID generating factory does not reset so each time setup goes it increments the ID
    }
    @Test
    public void testEditEvent() {
        IEvent event = calendar.getEvents().get(0);
        event.setTitle("new title");
        event.setDescription("new description");
        event.setTimeFrame(timeFrame2);
        assertEquals("new title", calendar.getEvents().get(0).getTitle());
        assertEquals("new description", calendar.getEvents().get(0).getDescription());
        assertEquals(timeFrame2, calendar.getEvents().get(0).getTimeFrame());
    }
    @Test
    public void testTagsEvent() {
        IEvent event = calendar.getEvents().get(3);
        IEvent event2 = calendar.getEvents().get(4);
        event.addTag("tag1");
        event.addTag("tag2");
        event.addTag("tag3");
        event2.addTag("tag1");
        assertEquals(3, calendar.getTags().size()); // test if tags are added to the calendar without duplicates
        assertEquals(3, event.getTags().size());
        assertEquals(1, event2.getTags().size());
        assertTrue(event.getTags().contains("tag1"));
        assertTrue(event.getTags().contains("tag2"));
        assertTrue(event2.getTags().contains("tag1"));
        event.removeTag("tag2");
        assertEquals(2, event.getTags().size());
        assertTrue(event.getTags().contains("tag1"));
        assertFalse(event.getTags().contains("tag2"));
        List<IEvent> tag1 = calendar.getEventsByTag("tag1");
        List<IEvent> tag2 = calendar.getEventsByTag("tag2");
        List<IEvent> tag3 = calendar.getEventsByTag("tag3");
        assertEquals(2, tag1.size());
        assertEquals(0, tag2.size());
        assertEquals(1, tag3.size());
        assertTrue(tag1.contains(event) && tag1.contains(event2));
        assertTrue(tag3.contains(event));
        assertFalse(tag2.contains(event) || tag2.contains(event2));

        assertEquals(2, calendar.getTags().size()); // test if tags are added to the calendar without duplicates


    }
    @Test
    public void testEventSorter(){
//        Upcoming
        List<IEvent> upcoming = calendar.getUpcomingEvents();
        List<IEvent> past = calendar.getPastEvents();
        assertEquals(10, upcoming.size());
        assertEquals(2, past.size());
        assertTrue(past.contains(event) && past.contains(calendar.getEvents().get(11)));
        assertTrue(upcoming.contains(calendar.getEvents().get(10)));

        assertTrue(event.getDateOfEvent().isBefore(upcoming.get(1).getDateOfEvent()));

        assertTrue(past.get(0).getDateOfEvent().isBefore(upcoming.get(0).getDateOfEvent()));
        List<IEvent> between = calendar.getEventsBetweenDates(start1.minusDays(1), end1.plusDays(1));
        assertEquals(2, between.size());
        List<IEvent> between2 = calendar.getEventsBetweenDates(start2.minusDays(1), end2.plusDays(1));
        assertEquals(10, between2.size());
        assertTrue(between.contains(event) && between.contains(calendar.getEvents().get(11)));
        assertTrue(between2.contains(calendar.getEvents().get(10)) && between2.contains(calendar.getEvents().get(1)));


    }
}