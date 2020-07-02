package it.unicam.cs.pa.jbudget105124.Model.Ledger;

import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;

import java.io.Serializable;
import java.util.List;

/**
 * Interfaccia cha sarà implementata dalle classi che hanno il compito di gestire un ledger.
 * Un ledger è un libro mastro contenente tutte le informazioni dei movimenti,transazioni,
 * account e tag.
 */
public interface Ledger extends Serializable {

	/**
	 * Metodo per ritornare la lista di account
	 * @return lista di account ritornata
	 */
	List<Account> getAccounts();

	/**
	 * Metodo per ritornare un singolo tag passato il suo ID
	 * @param ID ID del tag
	 * @return tag ritornato
	 */
	Tag getSingleTag(int ID);

	/**
	 * Metodo per ritornare un singolo account passato il suo ID
	 * @param ID ID dell'account
	 * @return account ritornato
	 */
	Account getSingleAccount(int ID);

	/**
	 * Metodo per ritornare un singolo movimento passato il suo ID
	 * e quello della sua transazione
	 * @param mID ID del movimento
	 * @param tID ID della transazione
	 * @return movimento ritornato
	 */
	Movement getSingleMovement(int mID,int tID);

	/**
	 * Metodo per ritornare una singola transazione passato il suo ID
	 * @param ID ID della transazione
	 * @return transazione ritornata
	 */
	Transaction getSingleTransaction(int ID);

	/**
	 * Metodo per aggiungere un movimento
	 * @param m movimento da aggiungere
	 */
	void addMovement(Movement m);

	/**
	 * Metodo per rimuovere un movimento
	 * @param m movimento da rimuovere
	 */
	void removeMovements(Movement m);

	/**
	 * Metodo per ritornare la lista di movimenti
	 * @return lista di movimenti ritornata
	 */
	List<Movement> getMovements();

	/**
	 * Metodo per aggiungere una transazione
	 * @param t transazione da aggiungere
	 */
	void addTransaction(Transaction t);

	/**
	 * Metodo per rimuovere una transazione
	 * @param t transazione da rimuovere
	 */
	void removeTransaction(Transaction t);

	/**
	 * Metodo per ritornare la lista di transazioni
	 * @return lista di transazioni
	 */
	List<Transaction> getTransactions();

	/**
	 * Metodo per ritornare la lista di tag
	 * @return lista di tag
	 */
	List<Tag> getTags();

	/**
	 * Metodo per aggiungere un accaunt
	 * @param a account da aggiungere
	 */
	void addAccount(Account a);

	/**
	 * Metodo per rimuovere un account
	 * @param a account da rimuovere
	 */
	void removeAccount(Account a);

	/**
	 * Metodo per aggiungere un tag
	 * @param t tag da aggiungere
	 */
	void addTag(Tag t);

	/**
	 * Metodo per rimuovere un tag
	 * @param t tag da rimuovere
	 */
	void removeTag(Tag t);	
}
