package ua.alex.threads.diningphilosopher;

import java.util.ArrayList;
import java.util.List;

import ua.alex.threads.diningphilosopher.Philosopher;
import ua.alex.threads.diningphilosopher.Waiter;

public class DiningPhilosophers {

	public static void main(String[] args) {

		Philosopher p1 = new Philosopher("Euclid (1)");
		Philosopher p2 = new Philosopher("Socrat (2)");
		Philosopher p3 = new Philosopher("Platon (3)");
		Philosopher p4 = new Philosopher("Aristotel (4)");
		Philosopher p5 = new Philosopher("Kant (5)");
		
		Thread philosopher1 = new Thread(p1, "Euclid (1)");
		Thread philosopher2 = new Thread(p2, "Socrat (2)");
		Thread philosopher3 = new Thread(p3, "Platon (3)");
		Thread philosopher4 = new Thread(p4, "Aristotel (4)");
		Thread philosopher5 = new Thread(p5, "Kant (5)");		
		
		List<Philosopher> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		
		Waiter waiter = new Waiter(list);
		Philosopher.setWaiter(waiter);
		
		
		philosopher1.start();
		philosopher2.start();
		philosopher3.start();
		philosopher4.start();
		philosopher5.start();
		
	}

}
