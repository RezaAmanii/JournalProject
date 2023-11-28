package org.group12.Observers.items_observers;

import org.group12.model.INameable;

public interface IItemObservable {
    void addItemObserver(IItemObserver observer);
    void removeItemObserver(IItemObserver observer);
    void notifyNewItem(INameable newItem);
    void notifyRemoveItem(String itemID);


}
