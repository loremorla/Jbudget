package it.unicam.cs.pa.jbudget105124.Model.BudgetReport;

import it.unicam.cs.pa.jbudget105124.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105124.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;

import java.io.Serializable;
import java.util.Map;

/**
 * Interfaccia cha sarà implementata dalle classi che hanno il compito di gestire un budget report,
 * cioè un strumento che preso un budget e un ledger ne stipula la differenza tra il budget aspettatosi
 * dall'utente e quello reale.
 */
public interface BudgetReport extends Serializable {

	/**
	 * Metodo per generare la differenza tra il budget aspettato
	 * ed il budget effettivo
	 * @return mappa dei budget riportanti la differenza
	 */
	Map<Tag,Double> report();

	/**
	 * Metodo per ritornare il ledger
	 * @return ledger ritornato
	 */
	Ledger getLedger();

	/**
	 * Metodo per ritornare il budget
	 * @return budget ritornato
	 */
	Budget getBudget();
}
