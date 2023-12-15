package org.group12.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class represents a collection of items.
 * It implements the ItemsSet interface and is serializable.
 */
public class Items implements ItemsSet, Serializable {
    private static final long serialVersionUID = 5735774274798693780L;
    private static Items instance = null;
    private final HashMap<String, INameable> itemMap;

    /**
     * Private constructor to prevent instantiation.
     * Initializes the itemMap.
     */
    private Items() {
        itemMap = new HashMap<>();
    }

    /**
     * Singleton pattern implementation.
     * If an instance does not exist, it creates one and returns it.
     * If an instance already exists, it simply returns it.
     *
     * @return the singleton instance of Items
     */
    public static Items getInstance() {
        if (instance == null) {
            instance = new Items();
        }
        return instance;
    }

    /**
     * Retrieves an item from the itemMap using its ID.
     *
     * @param id the ID of the item to retrieve
     * @return the item with the given ID
     */
    @Override
    public INameable getItem(String id) {
        return itemMap.get(id);
    }

    /**
     * Adds an item to the itemMap using its ID as the key.
     *
     * @param item the item to add
     */
    @Override
    public void addItem(INameable item) {
        itemMap.put(item.getID(), item);
    }

    /**
     * Removes an item from the itemMap using its ID.
     *
     * @param itemID the ID of the item to remove
     */
    @Override
    public void removeItem(String itemID) {
        itemMap.remove(itemID);
    }

    /**
     * Temporary method for testing.
     * Returns a string representation of the itemMap.
     *
     * @return a string representation of the itemMap
     */
    @Override
    public String toString() {
        return "Items{" +
                "itemMap=" + itemMap.toString() +
                '}';
    }
}