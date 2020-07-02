package it.unicam.cs.pa.jbudget105124.Model.BudgetReport;

import it.unicam.cs.pa.jbudget105124.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105124.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe che ha il compito di gestire un budget report.
 */
public class BudgetReportBasic implements BudgetReport {

	/**
	 * budget del budget Report
	 */
	private Budget budget;
	/**
	 * budget del budget Report
	 */
	private Ledger ledger;

	/**
	 * Costruttore del BudgetReportBasic
	 * @param b
	 * @param l
	 */
	public BudgetReportBasic(Budget b, Ledger l) {
		budget = b;
		ledger = l;
	}

	/**
	 * Metodo per generare la differenza tra il budget aspettato
	 * ed il budget effettivo
	 * @return mappa dei budget riportanti la differenza
	 */
	@Override
	public Map<Tag, Double> report() {
		Map<Tag,Double> result = new HashMap<>();
		budget.getTags().stream()
				.forEach(t->result.put(t,ledger.getSingleTag(t.getID()).getTotalAmount()-budget.getExpected(t)));
		return result;
	}

	/**
	 * Metodo per ritornare il ledger
	 * @return ledger ritornato
	 */
	@Override
	public Ledger getLedger() {
		return ledger;
	}

	/**
	 * Metodo per ritornare il budget
	 * @return budget ritornato
	 */
	@Override
	public Budget getBudget() {
		return budget;
	}

}
