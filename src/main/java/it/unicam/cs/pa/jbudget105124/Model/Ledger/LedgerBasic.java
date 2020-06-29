package it.unicam.cs.pa.jbudget105124.Model.Ledger;

import it.unicam.cs.pa.jbudget105124.Model.Utility;
import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Collection;

public class LedgerBasic implements Ledger {

	private List<Account> accounts;
	private List<Movement> movements;
	private List<Transaction> transactions;
	private List<Tag> tags;
	private Map<Tag, Double> totAmount;

	public LedgerBasic() {

		this.accounts = new ArrayList<>();
		this.movements = new ArrayList<>();
		this.transactions = new ArrayList<>();
		this.tags = new ArrayList<>();
		this.totAmount = new HashMap<>();

	}

	@Override
	public List<Account> getAccounts() {
		return accounts;
	}

	@Override
	public void setAccounts(List<Account> a) {
		accounts = a;
	}

	@Override
	public Account getSingleAccount(int ID) {
		return this.get(accounts, ID);
		/*AtomicReference<Account> singleAccount = new AtomicReference<>();
		accounts.stream().filter(a -> a.getID()==ID).forEach(a -> singleAccount.set(a));
		return singleAccount.get();*/
	}

	@Override
	public Tag getSingleTag(int ID) {
		return this.get(tags, ID);
		/*AtomicReference<Tag> singleTag = new AtomicReference<>();
		tags.stream().filter(t -> t.getID()==ID).forEach(t -> singleTag.set(t));
		return singleTag.get();*/
	}

	@Override
	public Movement getSingleMovement(int mID, int tID) {
		Transaction t = this.get(transactions, tID);
		return this.get(t.getMovements(), mID);
		/*AtomicReference<Movement> singleMovement = new AtomicReference<>();
		movements.stream().filter(m -> m.getID()==mID && m.getTransaction().getID() == tID)
						.forEach(m -> singleMovement.set(m));
		return singleMovement.get();*/
	}

	@Override
	public Transaction getSingleTransaction(int ID) {
		// return this.get(this.transactions,ID);
		return this.get(transactions, ID);
		/*AtomicReference<Transaction> singleTransaction = new AtomicReference<>();
		transactions.stream().filter(t -> t.getID()==ID).forEach(t -> singleTransaction.set(t));
		return singleTransaction.get();*/
	}
	
	/*@Override
	public double getRealBudgetTag(Tag t) {
		double amountTag = 0.0;
		movements.stream().filter(m->m.getTag()==t).forEach(m->m.getAmount()+amountTag);
		for(Movement m : movements){
			if(m.getTag()==t){
				amountTag = amountTag + m.getTag().getTotalAmount();
			}
		}
		for(Transaction tr : m.getTags()){
			t.removeMovement(m);
		}
	}*/

	/*@Override
	public void getBudgetEffettivo(Map<Tag, Double> be) {
		totAmount = be;
	}*/
	
	@Override
	public void addBudget(Tag t,double d){
		totAmount.put(t,d);
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
	public void setMovements(List<Movement> m) {
		movements = m;
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
	public void setTransactions(List<Transaction> t) {
		transactions = t;
	}

	//@Override // ??
	//public Transaction getTransaction(int ID/*Predicate<Transaction> p*/) {
		//return this.get(this.transactions,ID);
		//return transactions.stream().filter(p).collect(Collectors.<Transaction>toList());
	//}

	@Override
	public List<Tag> getTags() {
		return tags;
	}
	
	@Override
	public void setTags(List<Tag> t) {
		tags = t;
	}

	@Override
	public void addAccount(Account a) {
		accounts.add(a);
	}

	@Override
	public void removeAccount(Account a){
		// this.accounts.remove(account);
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
