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
				Thread.sleep(15);
			} catch (InterruptedException ex) {
				//
			}

		}
	}

	private synchronized int consume() throws InterruptedException {
		/*//wait if queue is empty
		if (sharedQueue.isEmpty()) {
			System.out.println("Queue is empty " + Thread.currentThread().getName()
					+ " is waiting , size: " + sharedQueue.size());
							
		}	*/	

		while (sharedQueue.isEmpty()) {
			this.wait();
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
		this.notifyAll();
		return result;

	}

}
