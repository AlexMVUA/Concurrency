package ua.alex.threads.threadpool;

import java.util.List;

public class WorkerThread implements Runnable {
	
	List<Runnable> taskQueue;	
	
	public WorkerThread() {
		
	}
	
	public WorkerThread(List<Runnable> taskQueue) {
		System.out.println("Created: " + Thread.currentThread().getName());
		this.taskQueue = taskQueue;
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
						taskQueue.wait();
						System.out.println("running:" + Thread.currentThread().getName());
					} catch (InterruptedException e) {
						//e.printStackTrace();
					}					
				}
				r = taskQueue.remove(0);
			}
			try {
				r.run();
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		
	}
 
}
