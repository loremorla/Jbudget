package it.unicam.cs.pa.jbudget105124.Model.BudgetReport;

import it.unicam.cs.pa.jbudget105124.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105124.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import java.util.HashMap;
import java.util.Map;

public class BudgetReportBasic implements BudgetReport {

	private Budget budget;
	private Ledger ledger;
	private Map<Tag,Double> result;
	
	public BudgetReportBasic(Budget b, Ledger l) {
		result = new HashMap<>();
		budget = b;
		ledger = l;
	}

	@Override
	public Map<Tag, Double> report() {
		result = new HashMap<>();
		budget.getTags().stream()
				.forEach(t->result.put(t,ledger.getSingleTag(t.getID()).getTotalAmount()-budget.getExpected(t)));
		return result;
	}

	@Override
	public Ledger getLedger() {
		return this.ledger;
	}

	@Override
	public Budget getBudget() {
		return this.budget;
	}

}
