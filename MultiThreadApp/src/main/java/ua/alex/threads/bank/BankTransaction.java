package ua.alex.threads.bank;

import java.util.Random;

public class BankTransaction implements Runnable {
	
	private final Account from;
	private final Account to;
	private final int transferSum;
	private static final Random random = new Random();
	private static volatile int successTransactions = 0;
	
	public BankTransaction(Account from, Account to, int transferSum) {
		this.from = from;
		this.to = to;
		this.transferSum = transferSum;
	}	
	
	@Override
	public void run() {
		if (from.equals(to)) {
			return;
		}
		synchronized (from) {
			synchronized (to) {
				if (from.withdraw(transferSum)) {
					try {
						Thread.sleep(random.nextInt(100));
						to.deposit(transferSum);
						successTransactions++;
					} catch (Exception e) {
						//from.deposit(transferSum);
						//to.withdraw(transferSum);
					} 
					
					System.out.println("OK. FROM: " + from + " TO: " + to + " Transfered: " + transferSum);					
				} else {
					System.out.println("Insufficient funds. Transaction CANCELLED. "
							+ "FROM: " + from + " TO: " + to + " Transfer sum: " + transferSum);
				}
					
			}
		}
		
		
	}
	
	public static int getSuccessTransactions() {
		return successTransactions;
	}
}
