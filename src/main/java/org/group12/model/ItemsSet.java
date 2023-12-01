package org.group12.model;

public interface ItemsSet {
    INameable getItem(String id);

    void addItem(INameable item);

    void removeItem(String itemID);
}
