package it.unicam.cs.pa.jbudget105124.Model.Transaction;

import java.util.*;
import java.util.function.Predicate;


public interface ScheduledTransaction {
	
	String getDescription();
	
	List<Transaction> getTransaction(Predicate<Transaction> p);
	
	boolean isCompleted();

}
