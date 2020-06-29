package it.unicam.cs.pa.jbudget105124.Model.Budget;

public interface BudgetManager {
	
	//BudgetReport generateReport(Ledger l,Budget b);
	static Budget createBudget(){
		return new BudgetTag();
	}

}
