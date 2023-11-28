import javafx.util.Pair;
import org.group12.controller.ControllerFactory;
import org.group12.controller.IController;
import org.group12.controller.JournalController;
import org.group12.model.Calendar.Calendar;
import org.group12.model.journal.Journal;
import org.group12.model.journal.JournalFactory;
import org.group12.view.JournalView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AllControllerTest {
    private JournalController journalController;
    private Journal journalModel;
    private JournalView journalView;

    @Test
    public void testAddEventWithValidInput(){
        Calendar calenderModel = new Calendar();

        assertDoesNotThrow(() ->
                calenderModel.addEvent("MyTitle", "School Project", LocalDateTime.now(),
                        new Pair<>(LocalDateTime.now(), LocalDateTime.now().plusHours(1))));
    }





















    // JournalController tests

    @BeforeEach
    public void setUp(){

    }

}
