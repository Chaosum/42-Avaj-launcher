package src;


import java.util.List;

import src.Aircraft.Flyable;

import java.util.ArrayList;

public class Tower {
	private List<Flyable> observers;
	
	public Tower() {
        observers = new ArrayList<Flyable>();
    }
	public void register(Flyable p_flyable) {
		observers.add(p_flyable);
	}
	public void	unregister(Flyable p_flyable) {
		observers.remove(p_flyable);
	}
	protected void	conditionChanged() {
		int i  = 0;
		while (i < observers.size()) {
			observers.get(i).updateConditions();
			i++;
		}
	}
}