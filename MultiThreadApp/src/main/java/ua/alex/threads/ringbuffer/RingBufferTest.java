package ua.alex.threads.ringbuffer;

import java.util.ArrayList;
import java.util.List;



public class RingBufferTest {

	public static final Integer QUEUE_SIZE = 8;

	public static void main(String[] args) throws InterruptedException {
		
		RingBuffer<String> ringBuffer = new RingBuffer<>(QUEUE_SIZE);
		
		List<Thread> threadList = new ArrayList<>();
		
		List<Consumer<String>> consumers = new ArrayList<>(); 		
		for (int i = 0; i < 4; i++) {
			Consumer<String> consumer = new Consumer<>(ringBuffer);
			consumers.add(consumer);
			threadList.add(new Thread(consumer, "Consumer " + i));				
		}
		
		List<Producer<String>> producers = new ArrayList<>(); 		
		for (int i = 0; i < 2; i++) {
			Producer<String> producer = new Producer<>(ringBuffer);
			producers.add(producer);
			threadList.add(new Thread(producer, "Producer " + i));			
		}		
		
		for (Thread thread : threadList) {
			thread.start();
		}
		
		Thread.sleep(15000);
		
		for (Thread thread : threadList) {
			thread.join();
		}
		
		System.out.println("Produced total: " + Producer.getCount());
	}

}
