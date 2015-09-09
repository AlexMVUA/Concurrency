package ua.alex.threads.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bank {
	
	private List<Account> accounts = new ArrayList<>();
	private String name;
	private static Random random = new Random();
	
	public Bank(String name, int numberAccounts) {		
		this.name = name;		
		for(int i = 0; i < numberAccounts; i++) {
			addAccount(new Account(i, random.nextInt(1000)));
		}
	}
	
	public void addAccount(Account account) {
		accounts.add(account);
	}
	
	public void remove(Account account) {
		accounts.remove(account);
	}
	
	public Account getRandomAccount() {
		return accounts.get(random.nextInt(accounts.size()));
	}
	
	public int calculateTotalBalance() {
		int result = 0;
		for(Account account : accounts) {
			result += account.getBalance();
		}
		return result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
