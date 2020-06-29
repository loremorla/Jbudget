package it.unicam.cs.pa.jbudget105124.Controller;

import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105124.Model.Budget.BudgetManager;
import it.unicam.cs.pa.jbudget105124.Model.Budget.BudgetTag;
import it.unicam.cs.pa.jbudget105124.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105124.Model.BudgetReport.BudgetReportBasic;
import it.unicam.cs.pa.jbudget105124.Model.BudgetReport.BudgetReportManager;
import it.unicam.cs.pa.jbudget105124.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105124.Model.Ledger.LedgerBasic;
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

public class SimpleController implements Controller {

	private BudgetReport budgetReport;

	public SimpleController(){
		budgetReport = new BudgetReportBasic(new BudgetTag(),new LedgerBasic());
		//resetBudgetReport();
	}

	@Override
	public void addTag(Tag t) {
		//this.budgetReport.getLedger().addTag(t);
		budgetReport.getLedger().addTag(t);
	}

	@Override
	public void removeTag(Tag t) {
		//this.budgetReport.getLedger().removeTag(tag);
		//removeBudgetRecord(tag);
		budgetReport.getLedger().removeTag(t);
	}

	@Override
	public Tag getTag(int ID) {
		//return this.budgetReport.getLedger().getTag(ID);
		return budgetReport.getLedger().getSingleTag(ID);
	}

	@Override
	public List<Tag> getTags() {
		//return this.budgetReport.getLedger().getTags();
		return budgetReport.getLedger().getTags();
	}

	@Override
	public void addMovement(Movement m) {
		m.getTransaction().addMovement(m);
		m.getTag().addMovement(m);
		m.getAccount().addMovement(m);
	}

	@Override
	public void removeMovement(Movement m) {
		m.getTransaction().removeMovement(m);
		m.getTag().removeMovement(m);
		/*for(Tag t : m.getTags()){
			t.removeMovement(m);
		}*/
		//m.getTag().removeMovement(m);
		m.getAccount().removeMovement(m);
	}

	@Override
	public Movement getMovement(int mID,int tID) {
		return budgetReport.getLedger().getSingleMovement(mID,tID);
	}

	@Override
	public List<Movement> getMovements() {
		//return this.budgetReport.getLedger().getTags();
		return budgetReport.getLedger().getMovements();
	}

	@Override
	public void addTransaction(Transaction t/*, List<Movement> ms*/) {
		/*if(!movements.isEmpty()&&transaction.getDate().toLocalDate().compareTo(LocalDate.now())>=0){
			transaction.addMovements(movements);
			this.budgetReport.getLedger().addTransaction(transaction);
			update();
			this.logger.fine("Addition of transaction: ["+transaction.toString()+"]");
		}else
			this.logger.fine("Failed in addition of transaction: ["+transaction.toString()+"]");*/
		budgetReport.getLedger().addTransaction(t);
		/*if(!ms.isEmpty()){
			t.addMovements(ms);
			ledger.addTransaction(t);
			//update();
		}*/
	}

	@Override
	public void removeTransaction(Transaction t) {
		//this.budgetReport.getLedger().removeTransaction(transaction);
		budgetReport.getLedger().removeTransaction(t);
	}

	@Override
	public Transaction getTransaction(int ID) {
		//return this.budgetReport.getLedger().getTransaction(ID);
		return budgetReport.getLedger().getSingleTransaction(ID);
	}

	@Override
	public List<Transaction> getTransactions() {
		return budgetReport.getLedger().getTransactions();
	}

	@Override
	public void addAccount(Account a) {
		// this.budgetReport.getLedger().addAccount(account);
		budgetReport.getLedger().addAccount(a);
	}

	@Override
	public void removeAccount(Account a) {
		//this.budgetReport.getLedger().removeAccount(account);
		budgetReport.getLedger().removeAccount(a);
	}

	@Override
	public Account getAccount(int ID) {
		return budgetReport.getLedger().getSingleAccount(ID);
	}

	@Override
	public List<Account> getAccounts() {
		return budgetReport.getLedger().getAccounts();
	}

	@Override
	public List<Transaction> scheduledTransactionsDate(LocalDate from, LocalDate to) {
		if(from.isBefore(to)) {
			List<Transaction> stransactions = new ArrayList<>();
			budgetReport.getLedger().getTransactions()
					.stream()
					.filter(t -> t.getDate().isAfter(from))
					.filter(t -> t.getDate().isBefore(to))
					.forEach(t -> stransactions.add(t));
			//this.logger.fine("Transactions scheduled from :["+start+"] to ["+stop+"]");
			return stransactions;
		}else {
			return null;
		}
		//this.logger.fine("Failed in scheduling transactions from :["+start+"] to ["+stop+"]");
	}

	@Override
	public List<Transaction> scheduledTransactionsTag(Tag t) {
		if(t != null) {
			List<Transaction> stransactions = new ArrayList<>();
			budgetReport.getLedger().getTransactions()
					.stream()
					.filter(tr -> tr.getTags().contains(t))
					.forEach(tr -> stransactions.add(tr));
			//this.logger.fine("Transactions scheduled by tag :["+tag.toString()+"]");
			return stransactions;
		} else{
			return null;
		}
		//this.logger.fine("Failed in scheduling transactions by tag :["+tag.toString()+"]");
	}

	@Override
	public void addBudgetTag(Tag t, Double amount) {
		if(t!=null && budgetReport.getLedger().getTags().contains(t)) {
			budgetReport.getBudget().add(t,amount);
			/*this.logger.fine("Addition of budget record with key: ["
					+tag.toString()+"] and value :["+value+"]");*/
		}/*else {
			this.logger.fine("Failed in addition of budget record with key: ["
					+tag.toString()+"] and value :["+value+"]");
		}*/
	}

	@Override
	public void removeBudgetTag(Tag t) {
		//this.budgetReport.getBudget().remove(tag);
		budgetReport.getBudget().remove(t);
	}

	@Override
	public Map<Tag, Double> getBudgetTags() {
		//return this.budgetReport.getBudget().getBudgetMap();
		return budgetReport.getBudget().getBudget();
	}

	@Override
	public Map<Tag,Double> checkBudget(){
		/*Map<Tag, Double> result = new HashMap<>();
		this.budgetReport.check().keySet().stream()
				.filter(t->this.budgetReport.check().get(t)<0)
				.forEach(t->result.put(t,this.budgetReport.check().get(t)));
		this.logger.fine("Check getter.");
		return result;*/
		return budgetReport.report();
		/*Map<Tag, Double> result = new HashMap<>();
        budget.getTags().stream().filter(t->budgetReport.getEffectiveBudget().containsKey(t))
                .forEach(t->result.put(t,budget.getExpected(t)+budgetReport.getEffectiveBudget().get(t)));
        budget.getTags().stream().filter(t->!budgetReport.getEffectiveBudget().containsKey(t))
        		.forEach(t->result.put(t,budget.getExpected(t)));
        return result;*/
	}

	@Override
	public void read(Reader reader) throws IOException, ClassNotFoundException {
		this.budgetReport = reader.read();
		reader.close();
	}

	@Override
	public void write(Writer writer) throws IOException {
		writer.write(this.budgetReport);
		writer.close();
	}

	@Override
	public void resetReport() {
		this.budgetReport = BudgetReportManager.createReport(LedgerManager.createLedger(), BudgetManager.createBudget());
	}
}
