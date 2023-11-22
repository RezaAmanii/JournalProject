import javafx.util.Pair;
import org.group12.model.Calendar.Calendar;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AllControllerTest {

    @Test
    public void testAddEventWithValidInput(){
        Calendar calenderModel = new Calendar();

        assertDoesNotThrow(() ->
                calenderModel.addEvent("MyTitle", "School Project", LocalDateTime.now(),
                        new Pair<>(LocalDateTime.now(), LocalDateTime.now().plusHours(1))));
    }
}
