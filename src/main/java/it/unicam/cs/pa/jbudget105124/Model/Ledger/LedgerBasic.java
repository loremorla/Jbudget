package it.unicam.cs.pa.jbudget105124.Model.Ledger;

import it.unicam.cs.pa.jbudget105124.Model.Utility;
import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Collection;

/**
 * Classe che ha lo scopo di gestire un ledger.
 */
public class LedgerBasic implements Ledger {

	/**
	 * Lista di account
	 */
	private List<Account> accounts;
	/**
	 * Lista di movimenti
	 */
	private List<Movement> movements;
	/**
	 * Lista di transazioni
	 */
	private List<Transaction> transactions;
	/**
	 * Lista di tag
	 */
	private List<Tag> tags;


	/**
	 * Costruttore del LedgerBasic
	 */
	public LedgerBasic() {

		this.accounts = new ArrayList<>();
		this.movements = new ArrayList<>();
		this.transactions = new ArrayList<>();
		this.tags = new ArrayList<>();

	}

	/**
	 * Metodo per ritornare la lista di account
	 * @return lista di account ritornata
	 */
	@Override
	public List<Account> getAccounts() {
		return accounts;
	}

	/**
	 * Metodo per ritornare un singolo account passato il suo ID
	 * @param ID ID dell'account
	 * @return account ritornato
	 */
	@Override
	public Account getSingleAccount(int ID) {
		return this.get(accounts, ID);
	}

	/**
	 * Metodo per ritornare un singolo tag passato il suo ID
	 * @param ID ID del tag
	 * @return tag ritornato
	 */
	@Override
	public Tag getSingleTag(int ID) {
		return this.get(tags, ID);
	}

	/**
	 * Metodo per ritornare un singolo movimento passato il suo ID
	 * e quello della sua transazione
	 * @param mID ID del movimento
	 * @param tID ID della transazione
	 * @return movimento ritornato
	 */
	@Override
	public Movement getSingleMovement(int mID, int tID) {
		Transaction t = this.get(transactions, tID);
		return this.get(t.getMovements(), mID);
	}

	/**
	 * Metodo per ritornare una singola transazione passato il suo ID
	 * @param ID ID della transazione
	 * @return transazione ritornata
	 */
	@Override
	public Transaction getSingleTransaction(int ID) {
		return this.get(transactions, ID);
	}

	/**
	 * Metodo per aggiungere un movimento
	 * @param m movimento da aggiungere
	 */
	@Override
	public void addMovement(Movement m) {
		movements.add(m);
	}

	/**
	 * Metodo per rimuovere un movimento
	 * @param m movimento da rimuovere
	 */
	@Override
	public void removeMovements(Movement m) {
		movements.remove(m);
	}

	/**
	 * Metodo per ritornare la lista di movimenti
	 * @return lista di movimenti ritornata
	 */
	@Override
	public List<Movement> getMovements(){
		return movements;
	}

	/**
	 * Metodo per aggiungere una transazione
	 * @param t transazione da aggiungere
	 */
	@Override
	public void addTransaction(Transaction t) {
		transactions.add(t);
	}

	/**
	 * Metodo per rimuovere una transazione
	 * @param t transazione da rimuovere
	 */
	@Override
	public void removeTransaction(Transaction t){
		transactions.remove(t);
	}

	/**
	 * Metodo per ritornare la lista di transazioni
	 * @return lista di transazioni
	 */
	@Override
	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * Metodo per ritornare la lista di tag
	 * @return lista di tag
	 */
	@Override
	public List<Tag> getTags() {
		return tags;
	}

	/**
	 * Metodo per aggiungere un accaunt
	 * @param a account da aggiungere
	 */
	@Override
	public void addAccount(Account a) {
		accounts.add(a);
	}

	/**
	 * Metodo per rimuovere un account
	 * @param a account da rimuovere
	 */
	@Override
	public void removeAccount(Account a){
		accounts.remove(a);
	}

	/**
	 * Metodo per aggiungere un tag
	 * @param t tag da aggiungere
	 */
	@Override
	public void addTag(Tag t) {
		tags.add(t);
	}

	/**
	 * Metodo per rimuovere un tag
	 * @param t tag da rimuovere
	 */
	@Override
	public void removeTag(Tag t) {
		tags.remove(t);
	}

	/**
	 * Metodo per controllare gli ID di una collection
	 * che estende Utility
	 * @param collection
	 * @param ID
	 * @param <T>
	 * @return eventuale ID da ritornare
	 */
	private <T extends Utility> T get(Collection<T> collection, int ID){
		AtomicReference<T> v = new AtomicReference<>();
		collection.stream().filter(t->t.getID()==ID).forEach(t->v.set(t));
		return v.get();
	}
}
