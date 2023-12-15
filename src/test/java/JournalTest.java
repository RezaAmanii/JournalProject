import org.group12.model.Container;
import org.group12.model.Items;
import org.group12.model.ItemsSet;
import org.group12.model.dataHandler.SaveLoad;
import org.group12.model.journal.*;
import org.group12.util.TextUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JournalTest {
    private Journal journal;
    private ItemsSet items;
    private LocalDate date1 = LocalDate.of(2022, 1, 1);
    private LocalDate date2 = LocalDate.of(2022, 1, 2);
    private JournalEntryFactory journalEntryFactoryInstance;
    private JournalFactory journalFactoryInstance;
    private List<IEntry> entries;

    @BeforeEach
    void setUp() {
        this.items = Items.getInstance();
        this.journalEntryFactoryInstance = JournalEntryFactory.getInstance();
        this.journalFactoryInstance = JournalFactory.getInstance();
        journal = journalFactoryInstance.createJournal("Test Journal", journalEntryFactoryInstance, items);
        journal.addEntry(date1);
        journal.addEntry(date2);
        this.entries= journal.getEntries();

    }

    @Test
    void testAddEntry() {
        assertEquals(2, entries.size(), "getEntries should return a list with all entries in the journal.");
        assertTrue(entries.stream().anyMatch(entry -> entry.getDateCreated().equals(date1)), "getEntries should include the entry with date1.");
        assertTrue(entries.stream().anyMatch(entry -> entry.getDateCreated().equals(date2)), "getEntries should include the entry with date2.");
    }



    @Test
    void testSetTitleAndGetTitle() {
        journal.setTitle("New Title");
        assertEquals("New Title", journal.getTitle(), "The title of the journal should match the new title.");
    }

    @Test
    void testGetID() {
        assertNotNull(journal.getID(), "The ID of the journal should not be null.");
    }

    @Test
    void testGetWordCount() {
        assertEquals(3, TextUtils.getWordCount("This is test"), "getWordCount should return the correct number of words in the string.");
        assertEquals(0, TextUtils.getWordCount(""), "getWordCount should return 0 for an empty string.");
        assertEquals(0, TextUtils.getWordCount(null), "getWordCount should return 0 for a null string.");
    }

    @Test
    void testGetFirstNWords() {
        assertEquals("This is test", TextUtils.getFirstNWords("This is test", 4), "getFirstNWords should return the first n words of the string.");
        assertEquals("This is", TextUtils.getFirstNWords("This is test", 2), "getFirstNWords should return the first n words of the string.");
        assertEquals("", TextUtils.getFirstNWords("", 2), "getFirstNWords should return an empty string if the input string is empty.");
        assertEquals("", TextUtils.getFirstNWords(null, 2), "getFirstNWords should return an empty string if the input string is null.");
    }

}