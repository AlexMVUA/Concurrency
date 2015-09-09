package ua.alex.threads;

public class SynchronizedSingleton {
	/**FIRST variant to create happens-before
	private static volatile SynchronizedSingleton instance;
	*/
	private static volatile SynchronizedSingleton instance;
	
	
	
	/**SECOND VARIANT 
	 * USING FINAL field
	 * any field with final 
	 * private final Object field1;
	 * 
	 */
	
	
	private SynchronizedSingleton() {		
		//field1 = new Object();
	}
	
	public static SynchronizedSingleton getInstance() {
		if (instance == null) {
			synchronized (SynchronizedSingleton.class) {
				if (instance == null) {
					instance = new SynchronizedSingleton();
				}
			}			
		}
		return instance;
	}
	
	
}
