package org.group12.model;

import org.group12.model.Calendar.Event;
import org.group12.model.journal.JournalEntry;
import org.group12.model.todo.IBigTask;
import org.group12.util.CastHelper;

import java.io.Serializable;
import java.util.HashMap;

public class Items implements ItemsSet, Serializable {
    private static final long serialVersionUID = 5735774274798693780L;
    private static Items instance = null;
    private final HashMap<String, INameable> itemMap;

    private Items() {
        itemMap = new HashMap<>();
    }

    public static Items getInstance() {
        if (instance == null) {
            instance = new Items();
        }
        return instance;
    }

    @Override
    public INameable getItem(String id) {
        return itemMap.get(id);
    }
    public IBigTask getBigTask(String id) {
        return CastHelper.castObject(IBigTask.class, id);
    }
    public Event getEvent(String id) {
        return CastHelper.castObject(Event.class, id);
    }
    public JournalEntry getJournalEntry(String id) {
        return CastHelper.castObject(JournalEntry.class, id); }

    @Override
    public void addItem(INameable item) {
        itemMap.put(item.getID(), item);
    }

    @Override
    public void removeItem(String itemID) {
        itemMap.remove(itemID);
    }

    // TODO temporary method for testing
    @Override
    public String toString() {
        return "Items{" +
                "itemMap=" + itemMap.toString() +
                '}';
    }
}
