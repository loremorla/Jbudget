package it.unicam.cs.pa.jbudget105124.Model.Budget;

import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;

import java.util.HashMap;
import java.util.List;

/**
 * Classe che ha il compito di gestire un budget associato ad ogni tag.
 */
public class BudgetTag implements Budget{

	/**
	 * Insieme dei budget
	 */
	private HashMap<Tag,Double> budget;

	/**
	 * Costruttore del BudgetTag
	 */
	public BudgetTag(){
		this.budget = new HashMap<>();
	}

	/**
	 * Metodo per aggiungere un budget ad un tag
	 * @param t tag a cui associare il budget
	 * @param amount amount del budget per il tag
	 */
	@Override
	public void add(Tag t,double amount){
		if(budget.containsKey(t))
			budget.remove(t);
		budget.put(t,amount);
	}

	/**
	 * Metodo per rimuovere un budget di un tag
	 * @param t tag a cui rimuovere il budget
	 */
	@Override
	public void remove(Tag t){
		budget.remove(t);
	}

	/**
	 * Metodo per ritornare la lista di tag
	 * @return lista di tag ritornata
	 */
	@Override
	public List<Tag> getTags() {
		return List.copyOf(budget.keySet());
	}

	/**
	 * Metodo per ritornare il budget di un tag
	 * @param t tag di cui si vuole l'amount
	 * @return amount del tag
	 */
	@Override
	public double getExpected(Tag t) {
		return budget.get(t);
	}

	/**
	 * Metodo che ritorna la mappa con tutti i budget dei tag
	 * @return mappa di budget ritornata
	 */
	@Override
	public HashMap<Tag,Double> getBudget() {
		return budget;
	}

}
