package ua.alex.threads.diningphilosopher;

public class DiningPhilosophers {

	public FoodFork[] forks;
    private Philosopher[] philosophers;
    private Waiter waiter;    

    public DiningPhilosophers(int philosophersNumber){
    	waiter = new Waiter(philosophersNumber);       
        forks = new FoodFork[philosophersNumber];
        philosophers = new Philosopher[philosophersNumber];
               
        for (int i = 0; i < philosophersNumber; i++){            
            forks[i] = new FoodFork();
        }
        
        for (int i = 0; i < philosophersNumber; i++){            
            philosophers[i] = new Philosopher(
            		i,
            		forks[i],
            		forks[(i+1) % philosophersNumber],
            		waiter);           
        }
    }
    
    public void startDinner() {
    	for (Philosopher p : philosophers) {
    		p.start();
    	}
    }
    
    public void finishDinner() throws InterruptedException {
    	for (Philosopher p : philosophers) {
    		p.stopDinning();
    		p.join();
    	}
    }
    
    public static void main(String[] args) throws InterruptedException {
        int personsNumber = 5; 
    	DiningPhilosophers dinner = new DiningPhilosophers(personsNumber);
        dinner.startDinner();
        
        Thread.sleep(10_000);
        
        dinner.finishDinner();
       
        
        for (Philosopher p : dinner.getPhilosophers()) {        	       	
        	System.out.println("philosopher " + p.getName()
        			+ " eats: " + p.getEatingCounter() + " times");
        }       
    }
    
    public Philosopher[] getPhilosophers() {
    	return philosophers;
    }
    
}
