package it.unicam.cs.pa.jbudget105124.Model.Movement;

import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Account.AccountType;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105124.Model.Utility;

import java.time.LocalDate;

public interface Movement extends Utility {
	
	int getID();
	
	void setID(int ID);

	LocalDate getDate();
	
	void setDate(LocalDate date);

	Tag getTag();

	//Tag getSingleTag(int ID);
	
	void setTag(Tag t);
	
	MovementType getMovementType();
	
	void setMovementType(MovementType mt);
	
	Transaction getTransaction();
	
	void setTransaction(Transaction t);
	
	AccountType getAccountType();
	
	double getAmount();
	
	void setAmount(double amount);
	
	Account getAccount();
	
	void setAccount(Account a);
	
	String getDescription();
	
	void setDescription(String description);
		
}
