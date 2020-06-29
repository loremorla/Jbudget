package it.unicam.cs.pa.jbudget105124.Model.Account;

import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Utility;

import java.util.List;

public interface Account extends Utility {
	
	String getName();
	
	void setName(String name);
	
	String getDescription();
	
	void setDescription(String d);
	
	int getID();
	
	void setID(int ID);
	
	AccountType getAccountType();
	
	void setAccountType(AccountType at);
	
	double getOpeningBalance();
	
	void setOpeningBalance(double ob);
	
	double getBalance();
	
	void setBalance(double balance);
	
	void incrementBalance(double amount);
	
	void decrementBalance(double amount);
	
	List<Movement> getMovements();
	
	void setMovements(List<Movement> m);
	
	void addMovement(Movement m);

	void removeMovement(Movement m);

	//List<Movement> getMovements(Predicate<Movement> p);
	
}
