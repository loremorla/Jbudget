package it.unicam.cs.pa.jbudget105124.Model.Transaction;

import it.unicam.cs.pa.jbudget105124.Model.Utility;
import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;

import java.time.LocalDate;
import java.util.List;

public interface Transaction extends Utility {
	
	int getID();
	
	void setID(int ID);
	
	List<Movement> getMovements();
	
	void setMovements(List<Movement> ms);
	
	void addMovement(Movement m);

	void addMovements(List<Movement> ms);
	
	void removeMovement(Movement m);
	
	List<Tag> getTags();
	
	//void setTags(List<Tag> t);
	
	//void addTag(Tag t);
	
	//void removeTag(Tag t);
	
	LocalDate getDate();
	
	void setDate(LocalDate date);

	String getDescription();

	void setDescription(String description);
	
	//boolean getState();
	
	//void setState(boolean state);

	//void completed();
	
	void addAmount(double amount);
	
	void decrementAmount(double amount);
	
	double getTotalAmount();
}
