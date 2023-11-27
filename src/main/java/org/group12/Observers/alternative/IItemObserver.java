package org.group12.Observers.alternative;

import org.group12.model.INameable;

public interface IItemObserver {
    void addItem(INameable item);
    void removeItem(String itemID);
}
