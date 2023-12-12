package org.group12.model.Calendar.futureprojects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Tags implements ITag, Serializable {
    private String ID;
    List<String> tags;

    public Tags(String id) {
        this.ID = id;
    }
    public void addTag(String tag){
        tags.add(tag);
    }

    public String getName() {
        return "name";
    }

    @Override
    public LocalDateTime getDateCreated() {
        return null;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public String getTitle() {
        return null;
    }
}
