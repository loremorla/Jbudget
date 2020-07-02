package it.unicam.cs.pa.jbudget105124.Model.Budget;

import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;

import java.io.Serializable;
import java.util.*;

/**
 * Interfaccia cha sarà implementata dalle classi che hanno il compito di gestire una serie di budget
 * ognuno associato ad tag.
 */
public interface Budget extends Serializable {

	/**
	 * Metodo per aggiungere un budget ad un tag
	 * @param t tag a cui associare il budget
	 * @param amount amount del budget per il tag
	 */
	void add(Tag t,double amount);

	/**
	 * Metodo per rimuovere un budget di un tag
	 * @param t tag a cui rimuovere il budget
	 */
	void remove(Tag t);

	/**
	 * Metodo per ritornare la lista di tag
	 * @return lista di tag ritornata
	 */
	List<Tag> getTags();

	/**
	 * Metodo per ritornare il budget di un tag
	 * @param t tag di cui si vuole l'amount
	 * @return amount del tag
	 */
	double getExpected(Tag t);

	/**
	 * Metodo che ritorna la mappa con tutti i budget dei tag
	 * @return mappa di budget ritornata
	 */
	HashMap<Tag, Double> getBudget();

}
