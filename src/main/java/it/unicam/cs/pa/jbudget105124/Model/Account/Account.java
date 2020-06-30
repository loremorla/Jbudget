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
	 * Metodo per impostare il nome dell'account
	 * @param name nome dell'account
	 */
	void setName(String name);

	/**
	 * Metodo per ritornare la descrizione dell'account
	 * @return descrizione account
	 */
	String getDescription();

	/**
	 * Metodo per impostare una descrizione all'account
	 * @param d descrizione dell'account
	 */
	void setDescription(String d);

	/**
	 * Metodo per ritornare l'ID dell'account
	 * @return ID account
	 */
	int getID();

	/**
	 * Metodo per impostare l'ID dell'account
	 * @param ID ID account
	 */
	void setID(int ID);

	/**
	 * Metodo per ritornare il tipo dell'account
	 * @return tipo dell'account
	 */
	AccountType getAccountType();

	/**
	 * Metodo per impostare il tipo dell'account
	 * @param at tipo dell'account
	 */
	void setAccountType(AccountType at);

	/**
	 * Metodo per ottenere il bilancio di apertura dell'account
	 * @return bilancio apertura account
	 */
	double getOpeningBalance();

	/**
	 * Metodo per impostare il bilancio di apertura dell'account
	 * @param ob bilancio apertura account
	 */
	void setOpeningBalance(double ob);

	/**
	 * Metodo per ottenere il bilancio dell'account
	 * @return bilancio dell'account
	 */
	double getBalance();

	/**
	 * Metodo per impostare il bilancio dell'account
	 * @param balance bilancio account
	 */
	void setBalance(double balance);

	/**
	 * Metodo per incrementare il bilancio
	 * @param amount somma da aggiungere
	 */
	void incrementBalance(double amount);

	/**
	 * Metodo per decrementare il bilancio
	 * @param amount somma da decrementare
	 */
	void decrementBalance(double amount);

	/**
	 * Metodo per ritornare la lista di movimenti
	 * @return lista di movimenti
	 */
	List<Movement> getMovements();

	/**
	 * Metodo per impostare la lista di movimenti
	 * @param m lista di movimenti
	 */
	void setMovements(List<Movement> m);

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
}
