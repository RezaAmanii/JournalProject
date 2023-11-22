package org.group12.Observers;

public interface IObservable {
    void addObserver(IPlanITObserver observer);
    void removeObserver(IPlanITObserver observer);
    void notifyObservers();

}
