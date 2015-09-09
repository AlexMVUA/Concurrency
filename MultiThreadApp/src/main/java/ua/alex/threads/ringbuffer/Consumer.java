package ua.alex.threads.ringbuffer;


public class Consumer<T> implements Runnable {

	private final RingBuffer<String> buffer;
	
	
	public Consumer(RingBuffer<String> sharedQueue) {
		this.buffer = sharedQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {				
				buffer.consume();				
			} catch (InterruptedException ex) {
				//
			}

		}
	}	

	
}
