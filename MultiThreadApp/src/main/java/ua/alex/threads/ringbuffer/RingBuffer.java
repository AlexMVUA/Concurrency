package ua.alex.threads.ringbuffer;


public class RingBuffer<T> {

	private T[] buffer;
	private int writeCount;
	private int readCount;
	private int unconsumedElements;

	@SuppressWarnings("unchecked")
	public RingBuffer(int bufferSize) {		
		buffer = (T[]) new Object[bufferSize];
		//size = bufferSize;		
	}

	synchronized public void produce(T element) throws InterruptedException {		

		while (writeCount == readCount && unconsumedElements == buffer.length ) {
			wait();
		}	

		buffer[writeCount] = element;
		writeCount = (writeCount + 1) % buffer.length;
		//writeCount++;
		/* Increase the number of unconsumed elements by one, then notify any
		 * threads that are waiting that more data is now available.
		 */
		unconsumedElements++;


		System.out.println(Thread.currentThread().getName() + " Produce (" + element + ")");
		notifyAll();

	}

	synchronized public T consume() throws InterruptedException {

		while (writeCount == readCount && unconsumedElements == 0 ) {
			wait();
		}

		T element = buffer[readCount];
		buffer[readCount] = null;
		readCount = (readCount + 1) % buffer.length;
		unconsumedElements--;
		notifyAll();
		System.out.println(Thread.currentThread().getName() + " Consumed (" + element + "}");
		return element;

	}

	public int capacity() {
		return buffer.length;
	}

	public synchronized int size() {
		return unconsumedElements;
	}

	public synchronized boolean isEmpty() {
		return size() == 0;
	}
}
