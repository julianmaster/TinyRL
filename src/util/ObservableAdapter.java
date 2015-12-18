package util;

import java.util.ArrayList;
import java.util.List;

public class ObservableAdapter implements Observable {
	private List<Observer> observers = new ArrayList<Observer>();

	@Override
	public void addObserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : this.observers) {
			observer.updateObserver(this);
		}
	}

	@Override
	public void deleteObservers() {
		this.observers.clear();
	}
}
