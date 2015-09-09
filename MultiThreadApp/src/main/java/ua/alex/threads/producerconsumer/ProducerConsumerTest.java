package ua.alex.threads.producerconsumer;

import java.util.ArrayDeque;

public class ProducerConsumerTest {

	public static final Integer QUEUE_SIZE = 8;	

	public static void main(String[] args) {
		
		ArrayDeque<Integer> sharedQueue = new ArrayDeque<>(QUEUE_SIZE);
		
		
		Thread prodThread1 = new Thread(new Producer(sharedQueue, QUEUE_SIZE), "Producer 1");        
		Thread prodThread2 = new Thread(new Producer(sharedQueue, QUEUE_SIZE), "Producer 2");
		Thread prodThread3 = new Thread(new Producer(sharedQueue, QUEUE_SIZE), "Producer 3");

		Thread consThread1 = new Thread(new Consumer(sharedQueue), "Consumer 1");
		Thread consThread2 = new Thread(new Consumer(sharedQueue), "Consumer 2");
		Thread consThread3 = new Thread(new Consumer(sharedQueue), "Consumer 3");
		
		consThread1.start();	
		consThread2.start();
		consThread3.start();
		
		prodThread1.start();
		prodThread2.start();
		prodThread3.start();

		
	}

}
