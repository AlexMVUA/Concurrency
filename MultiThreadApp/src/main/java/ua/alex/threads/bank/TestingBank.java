package ua.alex.threads.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestingBank {
	
	public static int TRANSACTION_QUANTITY = 1000;
	private static Random random = new Random();
	
	public static void main(String[] args) throws InterruptedException {

		Bank bank = new Bank("Delta", 100);
		int totalBankBalanceBeforeTransactions = bank.calculateTotalBalance();
		
				
		List<Thread> threadList = new ArrayList<>();
		
		for (int i = 0; i < TRANSACTION_QUANTITY; i++) {
			Account from = bank.getRandomAccount();
			Account to = bank.getRandomAccount();
			int randomTransferSum = random.nextInt(1000);
			threadList.add(new Thread(new BankTransaction(from, to, randomTransferSum)));			
		}
		
		for (Thread thread : threadList) {
			thread.start();
			thread.join();
		}
		System.out.println("Transactions finished");
		System.out.println("Total transactions: " + TRANSACTION_QUANTITY);
		System.out.println("Sucessful transactions: " + BankTransaction.getSuccessTransactions());
		
		System.out.println("Before transactions total balance in bank: " + totalBankBalanceBeforeTransactions);
		int totalBankBalanceAfterTransactions = bank.calculateTotalBalance();
		System.out.println("After  transactions total balance in bank: " + totalBankBalanceAfterTransactions);
		
	}

}
