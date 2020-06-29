package it.unicam.cs.pa.jbudget105124.Controller;

import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Store.Reader;
import it.unicam.cs.pa.jbudget105124.Model.Store.Writer;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface Controller {
	// TAG
	void addTag(Tag t);

	void removeTag(Tag t);

	Tag getTag(int ID);

	List<Tag> getTags();

	// MOVEMENTS
	void addMovement(Movement m);

	void removeMovement(Movement m);

	Movement getMovement(int mID,int tID);

	List<Movement> getMovements();

	//TRANSACTION
	void addTransaction(Transaction t);

	void removeTransaction(Transaction t);

	Transaction getTransaction(int ID);

	List<Transaction> getTransactions();

	//ACCOUNT
	void addAccount(Account a);

	void removeAccount(Account a);

	Account getAccount(int ID);

	List<Account> getAccounts();

	//SCHEDULED TRANSACTION
	List<Transaction> scheduledTransactionsDate(LocalDate from, LocalDate to);

	List<Transaction> scheduledTransactionsTag(Tag t);

	//BUDGET TAGS
	void addBudgetTag(Tag t, Double amount);

	void removeBudgetTag(Tag t);

	Map<Tag,Double> getBudgetTags();

	//CONTROLLA DIFFERENZA BUDGET EFFETTIVO E PRESTABILITO
	Map<Tag,Double> checkBudget();

	void read(Reader reader) throws IOException, ClassNotFoundException;

	void write(Writer writer) throws IOException;

	void resetReport();

	//DA IMPLEMENTARE
	//void resetBudgetTags();
	//void update();
	//void read(Reader<BudgetReport> reader) throws IOException;
	//void save(Writer<BudgetReport> writer) throws IOException;




	/*boolean checkDate(String Localdate);

	boolean checkDouble(String number);

	boolean checkAccountType(String account);

	boolean checkMovementType(String movement);

	boolean checkInt(String number);

	boolean checkIDTag(int id);

	boolean checkIDAccount(int id);

	boolean checkIDTransaction(int id);

	boolean checkIDMovement(int id,Transaction transaction);

	void updateAccount(double amount,Movement m);

	void updateBalance(Movement m,List<Tag> tags);

	Predicate<Transaction> checkFilter(String f);

	void compareDate(LocalDate date);*/

}
