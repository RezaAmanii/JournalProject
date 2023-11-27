package org.group12.model;

import org.group12.Observers.alternative.IItemObserver;

import java.util.HashMap;

public class Items implements IItemObserver {
    private final HashMap<String, INameable> itemMap;

    public Items() {
        itemMap = new HashMap<>();
    }

    public INameable getItem(String id) {
        return itemMap.get(id);
    }

    public void addItem(INameable item) {
        itemMap.put(item.getID(), item);
    }

    public void removeItem(String itemID) {
        itemMap.remove(itemID);
    }
}
