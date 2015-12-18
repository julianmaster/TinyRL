package util;

/**
 * Created by Julien on 18/02/14.
 */
public interface Observable {
    public void addObserver(Observer observer);
    public void notifyObservers();
    public void deleteObservers();
}
