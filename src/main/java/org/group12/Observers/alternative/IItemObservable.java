package org.group12.Observers.alternative;

import org.group12.model.INameable;

public interface IItemObservable {
    void addObserver(IItemObserver observer);
    void removeObserver(IItemObserver observer);
    void notifyNewItem(INameable newItem);
    void notifyRemoveItem(INameable newItem);


}
