package org.group12.model;

import java.util.Date;

public class Todo implements ITodo{

    // TODO find out how dates will be implemented and fix all date related sections

    String title;
    String description;
    Date dateCreated;
    Date dueDate;
    boolean status;

    public Todo(String title) {
        this.title = title;
        //this.dateCreated = ???
        this.status = false;
    }

    public Todo(String title, String description) {
        this(title);
        this.description = description;
    }

    public Todo(String title, String description, Date dueDate) {
        this(title, description);
        //this.dueDate = ???
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String desc) {
        this.description = desc;
    }

    @Override
    public String getDateCreated() {
        return null;
    }

    @Override
    public Date getDueDate() {
        return null;
    }

    @Override
    public void setDueDate(Date date) {

    }

    @Override
    public boolean getStatus() {
        return status;
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }
}
