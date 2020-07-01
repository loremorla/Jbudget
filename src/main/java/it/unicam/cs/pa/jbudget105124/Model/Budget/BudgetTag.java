package it.unicam.cs.pa.jbudget105124.Model.Budget;

import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BudgetTag implements Budget{
	
	private List<Tag> tags;
	private HashMap<Tag,Double> budget;
	
	public BudgetTag(){
		this.budget = new HashMap<>();
	}

	@Override
	public void add(Tag t,double amount){
		//if(!tags.contains(t)) tags.add(t);
		if(budget.containsKey(t))
			budget.remove(t);
		budget.put(t,amount);
	}

	@Override
	public void remove(Tag t){
		budget.remove(t);
		//tags.remove(t);
	}

	@Override
	public List<Tag> getTags() {
		return List.copyOf(budget.keySet());
	}
	
	@Override
	public void setTags(List<Tag> t) {
		tags = t;
	}

	/*@Override
	public void set(Tag t, double expected) {
		if(tags.contains(t)) budget.remove(t);
		else tags.add(t);
		budget.put(t,expected);
		
	}*/

	/*@Override
	public double getTag(Tag t) {
		return budget.get(t);
	}*/

	@Override
	public double getExpected(Tag t) {
		return budget.get(t);
	}
	
	@Override
	public HashMap<Tag,Double> getBudget() {
		return budget;
	}
	
	/*@Override
	public void getBudget(HashMap<Tag,Double> b) {
		budget = b;
	}*/

}
