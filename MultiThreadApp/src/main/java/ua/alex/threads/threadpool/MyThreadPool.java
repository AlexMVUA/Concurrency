package ua.alex.threads.threadpool;

import java.util.LinkedList;
import java.util.List;

public class MyThreadPool {

	private WorkerThread[] threads;
	private MyQueue<Runnable> taskQueue;	
	
	public MyThreadPool(int threadNumber) {
		threads = new WorkerThread[5];
		taskQueue = new MyQueue<>();		
	}	
	
	public void start() {
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new WorkerThread(taskQueue);
			System.out.println(threads[i]);		
			threads[i].start();			
		}			
	}
	
	public void addTask(Runnable r) {
		taskQueue.add(r);		
	}
	
	public void addAllTask(List<Runnable> list) {
		taskQueue.addAll(list);		
	}
	
	public void stop() {
		
		for (int i = 0; i < threads.length; i++) {			
			System.out.println("Stopped: " + threads[i]);						
			threads[i].stop();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		MyThreadPool pool = new MyThreadPool(5);		
		
		List<Runnable>  list = new LinkedList<>();
		
		for (int i = 0; i < 20000; i++) {		
			list.add(new Runnable() {
				@Override
				public void run() {
					
					System.out.println("Proccessing task...");
					for (int i = 0, count = 0; i < 1000000; i++) {
						count += i;
					}
					
				}
			});
		}		
		pool.addAllTask(list);
		pool.start();
		Thread.sleep(100000);
		pool.stop();
	}
	
	public MyQueue<Runnable> getTaskQueue() {
		return taskQueue;
	}	
}


