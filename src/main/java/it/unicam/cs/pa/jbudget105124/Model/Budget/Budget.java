package it.unicam.cs.pa.jbudget105124.Model.Budget;

import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;

import java.util.*;

public interface Budget {

	void add(Tag t,double amount);

	void remove(Tag t);
	
	List<Tag> getTags();
	
	void setTags(List<Tag> t);
	
	//void set(Tag t,double expected);
	
	double getExpected(Tag t);

	HashMap<Tag, Double> getBudget();
	
	//void getBudget(HashMap<Tag,Double> b);
}
