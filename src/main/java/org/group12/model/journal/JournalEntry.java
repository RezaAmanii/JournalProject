package org.group12.model;

import java.util.Date;

public class JournalEntry {
    private Long ID;
    private String title;
    private Date createdTimestamp;
    private Date modifiedTimestamp;
    private String content;

    /**
     * Constructor for creating a new JournalEntry object.
     *
     * @param ID               unique identifier for the journal entry
     * @param title            title of the journal entry
     * @param content          content of the journal entry
     * @param createdTimestamp timestamp when the journal entry was created
     */
    public JournalEntry(Long ID, String title, String content, Date createdTimestamp) {
        this.ID = ID;
        this.title = title;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = createdTimestamp;
        this.content = content;
    }

    /**
     * Removes the content from the journal entry.
     */
    public void removeContentFromEntry() {
        this.content = null;
    }

    /**
     * Updates the content of the journal entry and updates the modified timestamp.
     *
     * @param newContent the new content for the journal entry
     */
    public void updateContent(String newContent) {
        this.content = newContent;
        this.modifiedTimestamp = new Date();
    }

    /**
     * Updates the title of the journal entry and updates the modified timestamp.
     *
     * @param newTitle the new title for the journal entry
     */
    public void setTitle(String newTitle) {
        this.title = newTitle;
        this.modifiedTimestamp = new Date();
    }

    /**
     * Gets the unique identifier for the journal entry.
     *
     * @return the unique identifier for the journal entry
     */
    public Long getID() {
        return ID;
    }

    /**
     * Gets the title of the journal entry.
     *
     * @return the title of the journal entry
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the timestamp when the journal entry was created.
     *
     * @return the timestamp when the journal entry was created
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    /**
     * Gets the last modified timestamp for the journal entry.
     *
     * @return the last modified timestamp for the journal entry
     */
    public Date getModifiedTimestamp() {
        return modifiedTimestamp;
    }

    /**
     * Gets the content of the journal entry.
     *
     * @return the content of the journal entry
     */
    public String getContent() {
        return content;
    }
}