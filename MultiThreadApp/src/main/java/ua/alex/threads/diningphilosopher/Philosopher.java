package ua.alex.threads.diningphilosopher;

import java.util.Random;




import ua.alex.threads.diningphilosopher.Waiter;

public class Philosopher implements Runnable {
	
	private static  Waiter waiter;	
	private Random random;
	private String name;
	
	public Philosopher(String name) {
		this.name = name;
		random = new Random();
	}
		
	@Override
	public void run() {
		while(true) {
			int randomActionFlag = random.nextInt(100) % 2;			
			if (waiter.canIEat(this)/* && randomActionFlag == 0*/) {
				eat();
				
			} else {
				think();
			}
		}

	}
	
	public void eat() {
		try {
			Thread.sleep(random.nextInt(Waiter.maximumEatingTime));
			waiter.startEating(this);
			System.out.println("eating: " + Thread.currentThread().getName());
			System.out.println("NOW EATING: " + waiter.eatingPhilosophers());
		} catch (Exception e) {			
			//e.printStackTrace();
		}
		//waiter.increaseCountEat(this);
		waiter.finishEating(this);
	}
	
	public void think() {
		try {
			Thread.sleep(random.nextInt(Waiter.maximumThinkingTime));
			//Thread.sleep(100);
			//System.out.println("thinking: " + Thread.currentThread().getName() + " " + waiter.print());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public static Waiter getWaiter() {
		return waiter;
	}

	public static void setWaiter(Waiter waiter) {
		Philosopher.waiter = waiter;
	}

	public String getName() {
		return name;
	}
	
	
	
}