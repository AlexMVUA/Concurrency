package ua.alex.threads.diningphilosopher;

import java.util.ArrayList;
import java.util.List;

public class Waiter {
	
	private List<Philosopher> philosophers;
	private ArrayList<FoodFork> forks;
	private ArrayList<Boolean> isPhilosophersEating;
	public static final int SIZE = 5;
	public static final int maximumEatingTime = 10;
	public static final int maximumThinkingTime = 20;
	
	
	public Waiter(List<Philosopher> philosophers) {
		this.philosophers = philosophers;
		forks = new ArrayList<>(SIZE);
		isPhilosophersEating = new ArrayList<>(SIZE);
		for (int i = 0; i < SIZE; i++) {
			isPhilosophersEating.add(false);
		}
	}
	
	
	public boolean isHungry(Philosopher philosopher) {
		boolean result = false;		
		return result;
	}


	synchronized public boolean canIEat(Philosopher philosopher) {
		boolean allowEat = false;
		int philosopherIndex = philosophers.indexOf(philosopher);
		boolean leftP = false;
		boolean rightP = false;
		
		if (philosopherIndex == 0) {
			leftP = isPhilosophersEating.get(philosopherIndex + 1);
			rightP = isPhilosophersEating.get(SIZE - 1);
		} else if (philosopherIndex == SIZE - 1) {
			leftP = isPhilosophersEating.get(0);
			rightP = isPhilosophersEating.get(philosopherIndex - 1);
		} else {
			leftP = isPhilosophersEating.get(philosopherIndex + 1);
			rightP = isPhilosophersEating.get(philosopherIndex - 1);
		}
		
		if (!(leftP || rightP)) {
			allowEat = true;			
		}
		notifyAll();
		return allowEat;
	}
	
	synchronized public void startEating(Philosopher philosopher) {
		int philosopherIndex = philosophers.indexOf(philosopher);
		isPhilosophersEating.set(philosopherIndex, true);
		notifyAll();
	}


	public void finishEating(Philosopher philosopher) {
		int philosopherIndex = philosophers.indexOf(philosopher);		
			isPhilosophersEating.set(philosopherIndex, false);		
	}
	
	public String eatingPhilosophers() {
		StringBuilder sb = new StringBuilder();
		for (Boolean b : isPhilosophersEating) {
			if (b) {
				sb.append(b.toString().toUpperCase());
			} else {
				sb.append(b.toString());
			}
			sb.append(" ");
		}
		return sb.toString();
	}
	
}
