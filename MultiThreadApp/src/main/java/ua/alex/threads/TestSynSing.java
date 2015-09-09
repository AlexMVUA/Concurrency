package ua.alex.threads;

public class TestSynSing {

	public static void main(String[] args) {
		int count = 100;
		
		for (int i = 0; i < count; count++) {
			Thread t = new Thread(){
				public void run() {
					//SynchronizedSingleton.getInstance();
					System.out.println("i: " + SynchronizedSingleton.getInstance().hashCode());
				};				
			};			
			t.start();			
		}
	}

}
