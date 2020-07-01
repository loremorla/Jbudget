package it.unicam.cs.pa.jbudget105124.Model.Tag;

import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Utility;

import java.util.List;

public interface Tag extends Utility {
	
	String getName();
	
	void setName(String name);
	
	int getID();
	
	void setID(int ID);

	List<Movement> getMovements();

	double getTotalAmount();

	void addMovement(Movement m);

	void removeMovement(Movement m);

	String toString();

}
