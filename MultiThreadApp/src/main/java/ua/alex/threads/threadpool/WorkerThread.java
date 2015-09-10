package ua.alex.threads.threadpool;


public class WorkerThread implements Runnable {

	private MyQueue<Runnable> taskQueue;	
	private Thread thread;
	private volatile boolean running = false;
	
	
	public WorkerThread() {
		this.thread = new Thread(this);
	}

	public WorkerThread(MyQueue<Runnable> taskQueue) {
		this.thread = new Thread(this);
		System.out.println("Created: " + Thread.currentThread().getName());
		this.taskQueue = taskQueue;
	}

	@Override
	public void run() {
		Runnable r;
		while(running) {	
			try {							
				r = taskQueue.remove();				
				r.run();
			} catch (InterruptedException e) {
				System.out.println("Task was interrupted");
				return;
			}
		}
	}
	
	public void start() {
		thread.start();
		running = true;
	}
	
	public void stop() {
		thread.interrupt();
		running = false;
	}
}
