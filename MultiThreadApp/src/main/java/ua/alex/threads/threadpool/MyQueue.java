package ua.alex.threads.threadpool;

import java.util.LinkedList;
import java.util.List;

public class MyQueue<T> {
	
	private LinkedList<T> elements;
	private volatile int  totalElementsCount = 0;
	
	public MyQueue() {
		elements = new LinkedList<>();
	}
	
	public synchronized boolean isEmpty() {
		return elements.isEmpty();
	}
	
	public synchronized void add(T element) {
		totalElementsCount++;
		System.out.println("Added new Task (" + totalElementsCount + ")");
		elements.add(element);		
		notifyAll();
	}
	
	public synchronized T remove() throws InterruptedException {
		while (elements.isEmpty()) {
			this.wait();
		}		
		return elements.removeFirst();
	}

	public synchronized void addAll(List<T> list) {
		elements.addAll(list);
		notifyAll();
	}
}
