package org.group12.model.Calendar.interfaces;

import javafx.util.Pair;
import org.group12.model.*;

import java.time.LocalDateTime;

public interface IEvent extends INameable, IDateCreated, IDescription, IRecurrent, ITagable {

    Pair<LocalDateTime, LocalDateTime> getTimeFrame();
    LocalDateTime getDateOfEvent();
    void setTimeFrame(Pair<LocalDateTime, LocalDateTime> timeFrame);


}
