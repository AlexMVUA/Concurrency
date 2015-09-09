package ua.alex.threads.producerconsumer;

import java.util.ArrayDeque;

public class Consumer implements Runnable {

	private final ArrayDeque<Integer> sharedQueue;

	public Consumer(ArrayDeque<Integer> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {				
				consume();				
			} catch (InterruptedException ex) {
				//
			}

		}
	}

	private int consume() throws InterruptedException {
		synchronized(sharedQueue) {
			while (sharedQueue.isEmpty()) {
				sharedQueue.wait();
			}	
			StringBuilder sb = new StringBuilder();
			sb.append("Consumed by ");
			sb.append(Thread.currentThread().getName());
			sb.append(". Element: ");
			sb.append(sharedQueue.element());
			sb.append(". Queue size: ");
			sb.append(sharedQueue.size());			
			System.out.println(sb.toString());
			int result = sharedQueue.poll();
			sharedQueue.notifyAll();
			return result;
		}
	}

}
