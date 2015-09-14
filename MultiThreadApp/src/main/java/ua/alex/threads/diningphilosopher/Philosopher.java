package ua.alex.threads.diningphilosopher;

import java.util.Random;

public class Philosopher extends Thread {

	public static final int MAX_EAT_TIME = 1000;
	public static final int MAX_THINK_TIME = 3000;

	private int orderNumber;
	private Waiter waiter;
	private FoodFork leftFork;
	private FoodFork rightFork;
	private Random random;

	private int eatingCounter = 0;
	private boolean dining = true;


	public Philosopher(int order, FoodFork leftFork, FoodFork rightFork, Waiter waiter) {
		random = new Random();
		this.orderNumber = order;
		this.waiter = waiter;
		this.leftFork = leftFork;
		this.rightFork = rightFork;
	}

	@Override
	public void run() {
		while (dining) {    	
			try {
				think();
				eat();               
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	private void think() throws InterruptedException {
		Thread.sleep(random.nextInt(MAX_THINK_TIME));
	}

	private void eat() throws InterruptedException {
		//Trying to take forks, if forks are busy - then wait until they become free
		waiter.takeForks(orderNumber, leftFork, rightFork);
		// Eat for a random period of time
		Thread.sleep(random.nextInt(MAX_EAT_TIME));
		eatingCounter++;
		// Put down the forks, forks become available to other philosophers
		waiter.releaseForks(orderNumber, leftFork, rightFork);
	}

	public int getEatingCounter() {
		return eatingCounter;
	}

	public void stopDinning() {
		dining = false;
	}
}
