package org.group12.model;

import org.group12.IDateCreated;
import org.group12.INameable;

import java.time.LocalDateTime;
import java.util.Date;

public class JournalEntry implements INameable, IDateCreated {
    private Long ID;
    private String title;
    private LocalDateTime createdTimestamp;
    private LocalDateTime modifiedTimestamp;
    private String content;

    public JournalEntry(Long ID, String title, String content) {
        this.ID = ID;
        this.title = title;
        //this.createdTimestamp = new Date();
        //this.modifiedTimestamp = new Date();
        this.content = content;
    }
    public void updateContent(String newContent) {
        this.content = newContent;
        //this.modifiedTimestamp = new Date();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getID() {
        return ID;
    }

    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public LocalDateTime getDateCreated() {
        return createdTimestamp;
    }

    public LocalDateTime getModifiedTimestamp() {
        return modifiedTimestamp;
    }

    public String getContent() {
        return content;
    }
}
