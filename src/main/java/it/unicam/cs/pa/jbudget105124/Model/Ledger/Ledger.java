package it.unicam.cs.pa.jbudget105124.Model.Ledger;

import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;

import java.io.Serializable;
import java.util.List;

public interface Ledger extends Serializable {
	
	List<Account> getAccounts();
	
	void setAccounts(List<Account> a);
	
	Tag getSingleTag(int ID);
	
	Account getSingleAccount(int ID);

	Movement getSingleMovement(int mID,int tID);
	
	Transaction getSingleTransaction(int ID);
	
	//double getRealBudgetTag(Tag t);
	
	//void getBudgetEffettivo(Map<Tag, Double> be);
	
	void addMovement(Movement m);
	
	void removeMovements(Movement m);
	
	List<Movement> getMovements();
	
	void setMovements(List<Movement> m);
	
	void addTransaction(Transaction t);

	void removeTransaction(Transaction t);
	
	List<Transaction> getTransactions();
	
	void setTransactions(List<Transaction> t);
	
	//List<Transaction> getTransaction(Predicate<Transaction> p); //??
	
	List<Tag> getTags();
	
	void setTags(List<Tag> t);
	
	void addAccount(Account a);

	void removeAccount(Account a);
	
	void addTag(Tag t);

	void addBudget(Tag t, double d);

	void removeTag(Tag t);	
}
