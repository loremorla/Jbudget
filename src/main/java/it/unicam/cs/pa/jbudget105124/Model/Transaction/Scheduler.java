package it.unicam.cs.pa.jbudget105124.Model.Transaction;


import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Scheduler implements ScheduledTransaction {
	
	private String description;
	private List<Transaction> transactions;
	
	public Scheduler(String description,List<Transaction> t) {	
		this.description = description;
		this.transactions = t;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public List<Transaction> getTransaction(Predicate<Transaction> p) {
		if(p == null) return transactions.stream().collect(Collectors.<Transaction>toList());
		return transactions.stream().filter(p).collect(Collectors.<Transaction>toList());
	}

	@Override
	public boolean isCompleted() {
		return true;
	}

}
