package org.group12.model.journal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a search functionality for a Journal.
 */
public class JournalSearch implements Serializable {
    private Journal journal;
    private static final int MIN_SEARCH_LENGTH = 3;

    /**
     * Constructs a new JournalSearch.
     *
     * @param journal the journal to be searched
     */
    public JournalSearch(Journal journal) {
        this.journal = journal;
    }

    /**
     * Searches for entries in the journal that contain the specified keyword.
     * The search is case-insensitive and starts as soon as the keyword length is at least 3 characters.
     * If the keyword length is less than 3 characters, the method returns an empty list.
     *
     * @param keyword the keyword to search for in the journal entries
     * @return a list of JournalEntry objects that contain the keyword in their content
     */
    /*
    public List<JournalEntry> searchEntries(String keyword) {
        List<JournalEntry> matchingEntries = new ArrayList<>();
        if (keyword.length() < MIN_SEARCH_LENGTH) {
            return matchingEntries;
        }
        for (JournalEntry entry : journal.getEntries()) {
            if (entry.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                matchingEntries.add(entry);
            }
        }
        return matchingEntries;
    }

     */
}