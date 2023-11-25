package org.group12.model;

import java.util.HashMap;

public class Items {
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

    public void removeItem(INameable item) {
        itemMap.remove(item.getID());
    }
}
