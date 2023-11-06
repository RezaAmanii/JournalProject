package org.group12.model;

import java.util.Date;

public interface ITodo {
    String getTitle();
    void setTitle(String title);
    String getDescription();
    void setDescription(String desc);
    String getDateCreated();
    Date getDueDate();
    void setDueDate(Date date);
    boolean getStatus();
    void setStatus(boolean status);
}