package it.unicam.cs.pa.jbudget105124.Model.BudgetReport;

import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;

import java.util.Map;

public interface BudgetReport {
	
	//List<Tag> getTags();
	
	Map<Tag,Double> report();

	//Map<Tag,Double>  getEffectiveBudget();
	
	double getTag(Tag t);

}
