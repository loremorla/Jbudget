package it.unicam.cs.pa.jbudget105124.Controller;

import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Budget.BudgetManager;
import it.unicam.cs.pa.jbudget105124.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105124.Model.BudgetReport.BudgetReportManager;
import it.unicam.cs.pa.jbudget105124.Model.Ledger.LedgerManager;
import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Store.Reader;
import it.unicam.cs.pa.jbudget105124.Model.Store.Writer;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classe con ruolo da controller dell'MVC per coordinare le attività dell'applicazione.
 */
public class SimpleController implements Controller {

	/**
	 * BudgetReport del SimpleController
	 */
	private BudgetReport budgetReport;

	/**
	 * Costruttore della classe SimpleController
	 */
	public SimpleController(){
		budgetReport = BudgetReportManager.createReport(LedgerManager.createLedger(), BudgetManager.createBudget());
	}

	/**
	 *Metodo per aggiungere un tag
	 * @param t tag da aggiungere
	 */
	@Override
	public void addTag(Tag t) {
		budgetReport.getLedger().addTag(t);
	}

	/**
	 * Metodo per rimuovere un tag
	 * @param t tag da rimuovere
	 */
	@Override
	public void removeTag(Tag t) {
		for(Movement m : t.getMovements()){
			m.getTransaction().removeMovement(m);
			m.getAccount().removeMovement(m);
			budgetReport.getLedger().removeMovements(m);
		}
		budgetReport.getLedger().removeTag(t);
		removeBudgetTag(t);
	}

	/**
	 * Metodo per ritornare tutta la lista di tag
	 * @return lista di tag
	 */
	@Override
	public List<Tag> getTags() {
		return budgetReport.getLedger().getTags();
	}

	/**
	 * Metodo per aggiungere un movimento
	 * @param m movimento da aggiungere
	 */
	@Override
	public void addMovement(Movement m) {
		m.getTransaction().addMovement(m);
		m.getTag().addMovement(m);
		m.getAccount().addMovement(m);
		budgetReport.getLedger().addMovement(m);
	}

	/**
	 * Metodo per rimuovere un movimento
	 * @param m movimento da rimuovere
	 */
	@Override
	public void removeMovement(Movement m) {
		m.getTransaction().removeMovement(m);
		m.getTag().removeMovement(m);
		m.getAccount().removeMovement(m);
		budgetReport.getLedger().removeMovements(m);
	}

	/**
	 * Metodo per ritornare la lista di movimenti
	 * @return lista di movimenti
	 */
	@Override
	public List<Movement> getMovements() {
		return budgetReport.getLedger().getMovements();
	}

	/**
	 * Metodo per aggiungere una transazione
	 * @param t transazione da aggiungere
	 */
	@Override
	public void addTransaction(Transaction t) {
		budgetReport.getLedger().addTransaction(t);
	}

	/**
	 * Metodo per rimuovere la transazione
	 * @param t transazione da rimuovere
	 */
	@Override
	public void removeTransaction(Transaction t) {
		for(Movement m : t.getMovements()){
			m.getTag().removeMovement(m);
			m.getAccount().removeMovement(m);
			budgetReport.getLedger().removeMovements(m);
		}
		budgetReport.getLedger().removeTransaction(t);

	}

	/**
	 * Metodo per ritornare la lista di transazioni
	 * @return lista di transazioni
	 */
	@Override
	public List<Transaction> getTransactions() {
		return budgetReport.getLedger().getTransactions();
	}

	/**
	 * Metodo per aggiungere un account
	 * @param a account da aggiungere
	 */
	@Override
	public void addAccount(Account a) {
		budgetReport.getLedger().addAccount(a);
	}

	/**
	 * Metodo per rimuovere un account
	 * @param a account da rimuovere
	 */
	@Override
	public void removeAccount(Account a) {
		for(Movement m : a.getMovements()){
			m.getTransaction().removeMovement(m);
			m.getTag().removeMovement(m);
			budgetReport.getLedger().removeMovements(m);
		}
		budgetReport.getLedger().removeAccount(a);
	}

	/**
	 * Metodo per ritornare la lista di account
	 * @return lista di account
	 */
	@Override
	public List<Account> getAccounts() {
		return budgetReport.getLedger().getAccounts();
	}

	/**
	 * Metodo per ritornare una lista di transazioni limitata da due date
	 * @param from data di inizio
	 * @param to data di fine
	 * @return lista di transazioni filtrata
	 */
	@Override
	public List<Transaction> scheduledTransactionsDate(LocalDate from, LocalDate to) {
		if(!from.isAfter(to)) {
			List<Transaction> stransactions = new ArrayList<>();
			budgetReport.getLedger().getTransactions()
					.stream()
					.filter(t -> !t.getDate().isBefore(from))
					.filter(t -> !t.getDate().isAfter(to))
					.forEach(t -> stransactions.add(t));
			return stransactions;
		}else {
			return null;
		}
	}

	/**
	 * Metodo per ritornare una lista di transazioni filtrata con tag
	 * @param t tag per filtro
	 * @return lista di transazioni filtrata
	 */
	@Override
	public List<Transaction> scheduledTransactionsTag(Tag t) {
		if(t != null) {
			List<Transaction> stransactions = new ArrayList<>();
			budgetReport.getLedger().getTransactions()
					.stream()
					.filter(tr -> tr.getTags().contains(t))
					.forEach(tr -> stransactions.add(tr));
			return stransactions;
		} else{
			return null;
		}
	}

	/**
	 * Metodo per aggiungere un budget ad un tag
	 * @param t tag a cui creare il budget
	 * @param amount amout del budget
	 */
	@Override
	public void addBudgetTag(Tag t, Double amount) {
		if(t != null && budgetReport.getLedger().getTags().contains(t)) {
			budgetReport.getBudget().add(t,amount);
		}
	}

	/**
	 * Metodo per rimuovere un budget ad un tag
	 * @param t tag a cui rimuovere il budget
	 */
	@Override
	public void removeBudgetTag(Tag t) {
		budgetReport.getBudget().remove(t);
	}

	/**
	 * Metodo per ritornare tutta la mappa dei budget dei tag
	 * @return mappa di budget dei tag
	 */
	@Override
	public Map<Tag, Double> getBudgetTags() {
		return budgetReport.getBudget().getBudget();
	}

	/**
	 * Metodo per ritornare il Budget Report
	 * @return bydget ritornato
	 */
	@Override
	public BudgetReport getBudgetReport(){
		return budgetReport;
	}

	/**
	 * Metodo per leggere un Budget Report
	 * @param reader reader necessario per la lettura
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Override
	public void read(Reader reader) throws IOException, ClassNotFoundException {
		this.budgetReport = reader.read();
		reader.close();
	}

	/**
	 * Metodo per scrivere un Budget Report
	 * @param writer necessario per la scrittura
	 * @throws IOException
	 */
	@Override
	public void write(Writer writer) throws IOException {
		writer.write(this.budgetReport);
		writer.close();
	}

	/**
	 * Metodo per resettare il Budget Report
	 */
	@Override
	public void resetReport() {
		budgetReport = BudgetReportManager.createReport(LedgerManager.createLedger(), BudgetManager.createBudget());
	}
}
