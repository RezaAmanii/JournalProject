package org.group12.model;

import java.util.Date;

public class JournalEntry {
    private Long ID;
    private String title;
    private Date createdTimestamp;
    private Date modifiedTimestamp;
    private String content;

    public JournalEntry(Long ID, String title, String content) {
        this.ID = ID;
        this.title = title;
        this.createdTimestamp = new Date();
        this.modifiedTimestamp = new Date();
        this.content = content;
    }
}
