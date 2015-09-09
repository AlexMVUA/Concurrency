package ua.alex.threads.producerconsumer;

import java.util.ArrayDeque;

public class Producer implements Runnable {

	private final ArrayDeque<Integer> sharedQueue;
	private final int SIZE;
	public static int count = 0;

	public Producer(ArrayDeque<Integer> sharedQueue, int size) {
		this.sharedQueue = sharedQueue;
		this.SIZE = size;	
	}

	@Override
	public void run() {
		while (true) {
			try {
				produce(count);
				
			} catch (InterruptedException ex) {

			}

		}
	}

	 private synchronized void produce(int i) throws InterruptedException {	
			if (sharedQueue.size() == SIZE ) {
				System.out.println("Queue is full " + Thread.currentThread().getName()
						+ " is waiting , size: " + sharedQueue.size());
				while (sharedQueue.size() == SIZE) {
					wait();
				}				
			}
			count++;	
			sharedQueue.add(i);	

			StringBuilder sb = new StringBuilder();
			sb.append("Produced by ");
			sb.append(Thread.currentThread().getName());
			sb.append(". Element: ");
			sb.append(count);
			sb.append(". Queue size: ");
			sb.append(sharedQueue.size());			
			System.out.println(sb.toString());
			this.notifyAll();
		}
	

}
