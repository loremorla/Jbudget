package it.unicam.cs.pa.jbudget105124.Model.Tag;

import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Utility;

import java.util.List;

/**
 * Interfaccia che sarà implementata dalle classi che hanno il compito di gestire un Tag.
 * Un Tag rappresenta una categoria, una sorta di classe, di un movimento al quale ne possono essere associati
 * diversi.
 */
public interface Tag extends Utility {

	/**
	 * Metodo per ritornare il nome del Tag
	 * @return nome da ritornare
	 */
	String getName();

	/**
	 * Metodo per ritornare l'ID del Tag
	 * @return ID da ritornare
	 */
	int getID();

	/**
	 * Metodo per ritornare la lista di movimeni del Tag
	 * @return lista di movimenti
	 */
	List<Movement> getMovements();

	/**
	 * Metodo per ritornare l'amount totale riguardante il tag
	 * @return amount totale
	 */
	double getTotalAmount();

	/**
	 * Metodo per aggiungere un movimento alla lista di
	 * movimenti del Tag
	 * @param m movimento da aggiungere
	 */
	void addMovement(Movement m);

	/**
	 * Metodo per rimuovere un movimento dalla lista di
	 * movimenti del Tag
	 * @param m movimento da rimuovere
	 */
	void removeMovement(Movement m);

	/**
	 * Metodo per ritornare l'oggetto Tag in forma
	 * di stringa
	 * @return stringa dell'oggetto
	 */
	String toString();

}
