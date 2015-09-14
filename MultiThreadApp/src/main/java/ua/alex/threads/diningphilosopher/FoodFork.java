package ua.alex.threads.diningphilosopher;

public class FoodFork {
	
	private boolean free;

    public FoodFork() { 
        free = true;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean newState) {
        free = newState;
    }
}
