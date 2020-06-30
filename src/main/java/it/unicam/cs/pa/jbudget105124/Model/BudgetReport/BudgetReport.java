package it.unicam.cs.pa.jbudget105124.Model.BudgetReport;

import it.unicam.cs.pa.jbudget105124.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105124.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;

import java.io.Serializable;
import java.util.Map;

public interface BudgetReport extends Serializable {
	
	//List<Tag> getTags();
	
	Map<Tag,Double> report();

	//Map<Tag,Double>  getEffectiveBudget();
	
	double getTag(Tag t);

	double getEffectiveAmount(Tag t);

	Ledger getLedger();

	Budget getBudget();
}
