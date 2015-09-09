package ua.alex.threads.ringbuffer;

public class Producer<T> implements Runnable {

	private final RingBuffer<String> buffer;
	private static volatile int count;
	

	public Producer(RingBuffer<String> buffer) {
		this.buffer = buffer;
	}

	public static Integer getCount() {
		return count;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
			//	produce(i);
				count++;
				buffer.produce(Thread.currentThread().getName() + " " + count );
			} catch (InterruptedException ex) {

			}

		}
	}

}
