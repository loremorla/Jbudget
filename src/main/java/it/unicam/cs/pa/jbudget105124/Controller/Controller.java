package it.unicam.cs.pa.jbudget105124.Controller;

import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Store.Reader;
import it.unicam.cs.pa.jbudget105124.Model.Store.Writer;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Interfaccia che sarà implementata dalle classi che hanno il compito di fare da Controller del modello
 * MVC e che devono coordinare le varie componenti e funzionalità dell'applicazione.
 */
public interface Controller {

	/**
	 *Metodo per aggiungere un tag
	 * @param t tag da aggiungere
	 */
	void addTag(Tag t);

	/**
	 * Metodo per rimuovere un tag
	 * @param t tag da rimuovere
	 */
	void removeTag(Tag t);

	/**
	 * Metodo per ritornare tutta la lista di tag
	 * @return lista di tag
	 */
	List<Tag> getTags();

	/**
	 * Metodo per aggiungere un movimento
	 * @param m movimento da aggiungere
	 */
	void addMovement(Movement m);

	/**
	 * Metodo per rimuovere un movimento
	 * @param m movimento da rimuovere
	 */
	void removeMovement(Movement m);

	/**
	 * Metodo per ritornare la lista di movimenti
	 * @return lista di movimenti
	 */
	List<Movement> getMovements();

	/**
	 * Metodo per aggiungere una transazione
	 * @param t transazione da aggiungere
	 */
	void addTransaction(Transaction t);

	/**
	 * Metodo per rimuovere la transazione
	 * @param t transazione da rimuovere
	 */
	void removeTransaction(Transaction t);

	/**
	 * Metodo per ritornare la lista di transazioni
	 * @return lista di transazioni
	 */
	List<Transaction> getTransactions();

	/**
	 * Metodo per aggiungere un account
	 * @param a account da aggiungere
	 */
	void addAccount(Account a);

	/**
	 * Metodo per rimuovere un account
	 * @param a account da rimuovere
	 */
	void removeAccount(Account a);

	/**
	 * Metodo per ritornare la lista di account
	 * @return lista di account
	 */
	List<Account> getAccounts();

	/**
	 * Metodo per ritornare una lista di transazioni limitata da due date
	 * @param from data di inizio
	 * @param to data di fine
	 * @return lista di transazioni filtrata
	 */
	List<Transaction> scheduledTransactionsDate(LocalDate from, LocalDate to);

	/**
	 * Metodo per ritornare una lista di transazioni filtrata con tag
	 * @param t tag per filtro
	 * @return lista di transazioni filtrata
	 */
	List<Transaction> scheduledTransactionsTag(Tag t);

	/**
	 * Metodo per aggiungere un budget ad un tag
	 * @param t tag a cui creare il budget
	 * @param amount amout del budget
	 */
	void addBudgetTag(Tag t, Double amount);

	/**
	 * Metodo per rimuovere un budget ad un tag
	 * @param t tag a cui rimuovere il budget
	 */
	void removeBudgetTag(Tag t);

	/**
	 * Metodo per ritornare tutta la mappa dei budget dei tag
	 * @return mappa di budget dei tag
	 */
	Map<Tag,Double> getBudgetTags();

	/**
	 * Metodo per ritornare il Budget Report
	 * @return bydget ritornato
	 */
	BudgetReport getBudgetReport();

	/**
	 * Metodo per leggere un Budget Report
	 * @param reader reader necessario per la lettura
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void read(Reader reader) throws IOException, ClassNotFoundException;

	/**
	 * Metodo per scrivere un Budget Report
	 * @param writer necessario per la scrittura
	 * @throws IOException
	 */
	void write(Writer writer) throws IOException;

	/**
	 * Metodo per resettare il Budget Report
	 */
	void resetReport();
}
