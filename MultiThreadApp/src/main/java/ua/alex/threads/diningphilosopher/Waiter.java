package ua.alex.threads.diningphilosopher;

public class Waiter {

	private String[] state;
	private final int PHILOSOPHERS_NUMBER;


	private void printState() {
		for (int i = 0; i < PHILOSOPHERS_NUMBER; i++) {
			System.out.printf("%-10s |" , state[i]);
		}
		System.out.println();
	}

	public Waiter(int philosophersNumber) {
		this.PHILOSOPHERS_NUMBER = philosophersNumber;
		state = new String[PHILOSOPHERS_NUMBER];

		for (int i = 0; i < PHILOSOPHERS_NUMBER; i++) {            
			state[i] = "Start";
		}
		printState();
	}

	public synchronized void takeForks(int id, FoodFork leftFork, FoodFork rightFork) {
		try{            
			state[id] = "Wait";
			while(!leftFork.isFree() || !rightFork.isFree()){
				wait();
			}
			// If they are free, take the forks
			leftFork.setFree(false);
			rightFork.setFree(false);
			state[id] = "EAT";
			printState();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void releaseForks(int id, FoodFork leftFork, FoodFork rightFork) {       
		state[id] = "Think";
		leftFork.setFree(true);
		rightFork.setFree(true);
		printState();
		notifyAll();
	}
}
