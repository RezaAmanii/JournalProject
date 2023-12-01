import org.group12.model.journal.*;
import org.group12.util.TextUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JournalTest {
    private Journal journal;
    private JournalFactory journalFactory;
    private JournalEntryFactory journalEntryFactory;
    private JournalEntry journalEntry;

    @BeforeEach
    void setUp() {
        journalEntryFactory = JournalEntryFactory.getInstance();
        journalFactory = JournalFactory.getInstance();
        journal = journalFactory.createJournal("Test Journal", journalEntryFactory);
        journalEntry = journalEntryFactory.createJournalEntry("Test Title", "Test Content");
        journal.addEntry("Test Title", "Test Content");
    }

    @Test
    void testAddEntry() {
        assertEquals(1, journal.getEntries().size(), "Journal should have one entry after adding an entry.");
    }

    @Test
    void testGetEntries() {
        JournalEntry addedEntry = journal.getEntries().get(0);
        assertEquals("Test Title", addedEntry.getTitle(), "The title of the added entry should match the provided title.");
        assertEquals("Test Content", addedEntry.getContent(), "The content of the added entry should match the provided content.");
    }

    @Test
    void testRemoveEntry() {
        JournalEntry addedEntry = journal.getEntries().get(0);
        journal.removeEntry(addedEntry);
        assertEquals(0, journal.getEntries().size(), "Journal should have no entries after removing the added entry.");
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
    @Test
    void testGetTitle() {
        assertEquals("Test Title", journalEntry.getTitle(), "getTitle should return the correct title.");
    }

    @Test
    void testGetContent() {
        assertEquals("Test Content", journalEntry.getContent(), "getContent should return the correct content.");
    }

    @Test
    void testGetEntryID() {
        assertNotNull(journalEntry.getID(), "The ID of the journal should not be null.");
    }

    @Test
    void testSetTitle() {
        journalEntry.setTitle("New Title");
        assertEquals("New Title", journalEntry.getTitle(), "setTitle should update the title correctly.");
    }

    @Test
    void testSetContent() {
        journalEntry.updateContent("New Content");
        assertEquals("New Content", journalEntry.getContent(), "updateContent should update the content correctly.");
    }

    @Test
    void testGetDate() {
        assertNotNull(journalEntry.getDateCreated(), "getDateCreated should not return null.");
    }

}