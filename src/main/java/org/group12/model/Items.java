package org.group12.model;

import java.util.HashMap;

public class Items implements ItemsSet{
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

    @Override
    public void addItem(INameable item) {
        itemMap.put(item.getID(), item);
    }

    @Override
    public void removeItem(String itemID) {
        itemMap.remove(itemID);
    }
}
