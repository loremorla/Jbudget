package it.unicam.cs.pa.jbudget105124.Model.Budget;

/**
 * Interfaccia che ha il compito di creare un Budget.
 */
public interface BudgetManager {

	/**
	 * Metodo per generare un BudgetTag.
	 * @return BudgetTag generato.
	 */
	static Budget createBudget(){
		return new BudgetTag();
	}

}
