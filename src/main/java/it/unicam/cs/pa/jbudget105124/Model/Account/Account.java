package it.unicam.cs.pa.jbudget105124.Model.Account;

import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Utility;

import java.util.List;

/**
 * Interfaccia che sarà implementata dalle classi che hanno il compito di gestire un account.
 */
public interface Account extends Utility {

	/**
	 * Metodo per ritornare il nome dell'account
	 * @return nome account
	 */
	String getName();

	/**
	 * Metodo per ritornare la descrizione dell'account
	 * @return descrizione account
	 */
	String getDescription();

	/**
	 * Metodo per ritornare l'ID dell'account
	 * @return ID account
	 */
	int getID();

	/**
	 * Metodo per ritornare il tipo dell'account
	 * @return tipo dell'account
	 */
	AccountType getAccountType();

	/**
	 * Metodo per ottenere il bilancio di apertura dell'account
	 * @return bilancio apertura account
	 */
	double getOpeningBalance();

	/**
	 * Metodo per ottenere il bilancio dell'account
	 * @return bilancio dell'account
	 */
	double getBalance();

	/**
	 * Metodo per ritornare la lista di movimenti
	 * @return lista di movimenti
	 */
	List<Movement> getMovements();

	/**
	 * Metodo per aggiungere un movimento all'account
	 * @param m movimento da aggiungere
	 */
	void addMovement(Movement m);

	/**
	 * Metodo per rimuovere un movimento all'account
	 * @param m movimento da rimuovere
	 */
	void removeMovement(Movement m);

	/**
	 * Metodo per ritornare l'oggetto Account in forma
	 * di stringa
	 * @return stringa dell'oggetto
	 */
	String toString();
}
