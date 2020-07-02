package it.unicam.cs.pa.jbudget105124.Model.Budget;

import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;

import java.util.HashMap;
import java.util.List;

public class BudgetTag implements Budget{

	private HashMap<Tag,Double> budget;
	
	public BudgetTag(){
		this.budget = new HashMap<>();
	}

	@Override
	public void add(Tag t,double amount){
		if(budget.containsKey(t))
			budget.remove(t);
		budget.put(t,amount);
	}

	@Override
	public void remove(Tag t){
		budget.remove(t);
	}

	@Override
	public List<Tag> getTags() {
		return List.copyOf(budget.keySet());
	}

	@Override
	public double getExpected(Tag t) {
		return budget.get(t);
	}
	
	@Override
	public HashMap<Tag,Double> getBudget() {
		return budget;
	}

}
