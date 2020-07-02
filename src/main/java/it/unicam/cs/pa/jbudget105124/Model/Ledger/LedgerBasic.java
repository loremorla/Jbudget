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

public class LedgerBasic implements Ledger {

	private List<Account> accounts;
	private List<Movement> movements;
	private List<Transaction> transactions;
	private List<Tag> tags;

	public LedgerBasic() {

		this.accounts = new ArrayList<>();
		this.movements = new ArrayList<>();
		this.transactions = new ArrayList<>();
		this.tags = new ArrayList<>();

	}

	@Override
	public List<Account> getAccounts() {
		return accounts;
	}

	@Override
	public Account getSingleAccount(int ID) {
		return this.get(accounts, ID);
	}

	@Override
	public Tag getSingleTag(int ID) {
		return this.get(tags, ID);
	}

	@Override
	public Movement getSingleMovement(int mID, int tID) {
		Transaction t = this.get(transactions, tID);
		return this.get(t.getMovements(), mID);
	}

	@Override
	public Transaction getSingleTransaction(int ID) {
		return this.get(transactions, ID);
	}
	
	@Override
	public void addMovement(Movement m) {
		movements.add(m);
	}
	
	@Override
	public void removeMovements(Movement m) {
		movements.remove(m);
	}
	
	@Override
	public List<Movement> getMovements(){
		return movements;
	}

	@Override
	public void addTransaction(Transaction t) {
		transactions.add(t);
	}

	@Override
	public void removeTransaction(Transaction t){
		transactions.remove(t);
	}

	@Override
	public List<Transaction> getTransactions() {
		return transactions;
	}

	@Override
	public List<Tag> getTags() {
		return tags;
	}

	@Override
	public void addAccount(Account a) {
		accounts.add(a);
	}

	@Override
	public void removeAccount(Account a){
		accounts.remove(a);
	}

	@Override
	public void addTag(Tag t) {
		tags.add(t);
	}
	
	@Override
	public void removeTag(Tag t) {
		tags.remove(t);
	}

	private <T extends Utility> T get(Collection<T> collection, int ID){
		AtomicReference<T> v = new AtomicReference<>();
		collection.stream().filter(t->t.getID()==ID).forEach(t->v.set(t));
		return v.get();
	}
}
