package org.group12.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import org.group12.controller.JournalController;
import org.group12.model.Calendar.Event;
import org.group12.model.ItemsSet;
import org.group12.model.journal.JournalEntry;

import java.util.List;

public class CardUpdater {
    private final ItemsSet items;

    public CardUpdater(ItemsSet items) {
        this.items = items;
    }
    public void updateTodoListCard(TodoListCard card) {
        // ... existing update code from TodoListCard ...
    }
    public void updateTaskListCard(TaskListCard card) {
        // ... existing update code from TaskListCard ...
    }
    public void updateBigTaskCard(BigTaskCard card) {
        // ... existing update code from BigTaskCard ...
    }
    public void updateTaskCard(TaskCard card) {
        // ... existing update code from TaskCard ...
    }

    public void updateJournalEntryCard(JournalEntry entry, JournalController controller, Label titleLabel, TextArea contentArea, Label dateModified, Label NrOfWords) {
        // ... existing update code from JournalEntryCard ...
        if (entry != null) {
            // Set title
            String title = controller.getEntryTitle(entry);
            titleLabel.setText(title);
            // Set content
            String content = controller.getEntryContent(entry);
            contentArea.setText(content);
//            Set datemodified
            String datemod = controller.getEntryDateModified(entry);
            dateModified.setText(datemod);
//            Set wordcount
            String words = controller.getNrOfWords(entry);
            NrOfWords.setText(words);
        } else {
            // If entry is null, print an error message
            System.out.println("Entry with ID " + entry.getID() + " is null!");
        }
    }
    public void updateEventCard(String ID, Event event, Label titleLabel, Label contentLabel) {
        // ... existing update code from EventCard ...
        // Check if event is not null
        if (event != null) {
            // Set title
            String title = event.getTitle();
            titleLabel.setText(title);
            // Set content
            String content = event.getDescription();
            contentLabel.setText(content);
        } else {
            // If event is null, print an error message
            System.out.println("Event with ID " + ID + " is null!");
        }
    }
    public void updateEventCardTiny(Event event, String ID, Label titleLabel, FlowPane tagsFlowPane) {
        // ... existing update code from EventCardTiny ...
        // Check if event is not null
        if (event != null) {
            // Set title
            String title = event.getTitle();
            titleLabel.setText(title);
            // Set tags
            List<String> tags = event.getTags();
            int maxTagsToShow = 3;
            for (int i = 0; i < tags.size() && i < maxTagsToShow; i++) {
                Label tagLabel = new Label(tags.get(i));
                tagLabel.setTextFill(Color.BLUE); // set tag colour, in future this should be set to the colour of the tag
                tagsFlowPane.getChildren().add(tagLabel);
            }
        } else {
            // If event is null, print an error message
            System.out.println("Event with ID " + ID + " is null!");
        }
    }






}


