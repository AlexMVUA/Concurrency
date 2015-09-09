package ua.alex.threads.threadpool;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AnotherThreadPool {

	private WThread[] threads;
	private List<Runnable> taskQueue;
	
	public AnotherThreadPool(int threadNumber) {
		threads = new WThread[5];
		taskQueue = new LinkedList<>();
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new WThread();
			Thread t = new Thread(threads[i]);
			t.start();
		}
		
		for (int i = 0; i < 250; i++) {		
			new WThread().enqueue(new Thread());
		}		
	}	
	
	public static void main(String[] args) {
		AnotherThreadPool pool = new AnotherThreadPool(5);		
		
		
	}
	
	class WThread implements Runnable {
		
		public WThread() {
			System.out.println("Created: " + Thread.currentThread().getName());
		}
			
		public void enqueue(Runnable r) {
			synchronized(taskQueue) {
				taskQueue.add(r);
				taskQueue.notify();
			}
		}
		
		@Override
		public void run() {
			Runnable r;
			while(true) {
				synchronized(taskQueue) {
					while (taskQueue.isEmpty()) {
						try {
							System.out.println("busy:" + Thread.currentThread().getName());
							taskQueue.wait();							
						} catch (InterruptedException e) {	
							e.printStackTrace();;
						}					
					}
					try {
						Thread.sleep(new Random().nextInt(2000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					r = taskQueue.remove(0);
				}
				try {
					System.out.println("Starting work: " + Thread.currentThread().getName());
					r.run();
					System.out.println("Finishes work: " + Thread.currentThread().getName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	 
	}
}


