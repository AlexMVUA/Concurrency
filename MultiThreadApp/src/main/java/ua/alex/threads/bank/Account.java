package ua.alex.threads.bank;

public class Account {
	
	private final long id;
	private int balance;
	
	public Account(long id, int balance) {
		this.id = id;
		this.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public long getId() {
		return id;
	}
	
	public void deposit(int sum) {		
		this.balance += sum;
	}
	
	public boolean withdraw(int sum) {
		if (balance >= sum) {
			balance -= sum;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public String toString() {
		return "Acc.(" + id + ") : " + balance + " Usd.";
	}
}
