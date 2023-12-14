package org.group12.model.journal;

import org.group12.Observers.IObservable;
import org.group12.model.INameable;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IEntry extends INameable, IObservable, Serializable {
    void updateContent(String newContent);
    LocalDate getDateCreated();
    LocalDateTime getModifiedTimestamp();
    String getContent();
}