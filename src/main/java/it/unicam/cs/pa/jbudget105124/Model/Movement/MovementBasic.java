package it.unicam.cs.pa.jbudget105124.Model.Movement;

import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Account.AccountType;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;

import java.time.LocalDate;

public class MovementBasic implements Movement {
	
	private int ID;
	private LocalDate date;
	private double amount;
	private String description;
	private Tag tag;
	private Account account;
	private AccountType accountType;
	private MovementType movementType;
	private Transaction transaction;
	
	/*public MovementBasic(){
		this.tag = new ArrayList<>();
	}*/
	
	public MovementBasic(int ID, double amount,String description,Account account,
			Tag tag,MovementType mt,Transaction t) {
		this.ID = ID;
		this.date = t.getDate();
		this.amount = amount;
		this.description = description;
		this.tag = tag;
		this.accountType = account.getAccountType();
		this.movementType = mt;
		this.transaction = t;
		this.account = account;
	}
	
	@Override
	public int getID() {
		return ID;
	}
	
	@Override
	public void setID(int ID) {
		this.ID = ID;
	}

	@Override
	public LocalDate getDate() {
		return date;
	}
	
	@Override
	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public Tag getTag() {
		return tag;
	}
	
	@Override
	public void setTag(Tag t) {
		tag = t;
	}

	/*@Override
	public Tag getSingleTag(int ID){
		return null;
	}*/

	@Override
	public double getAmount() {
		return amount;
	}

	@Override
	public double getRealAmount() {
		if(movementType.equals(MovementType.DEBIT)){
			return -amount;
		}
		else return amount;
	}
	
	@Override
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Override
	public Account getAccount() {
		return account;
	}
	
	@Override
	public void setAccount(Account a) {
		account = a;
		accountType = a.getAccountType();
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public MovementType getMovementType() {
		return movementType;
	}
	
	@Override
	public void setMovementType(MovementType mt) {
		movementType = mt;
	}

	@Override
	public AccountType getAccountType() {
		return accountType;
	}

	@Override
	public Transaction getTransaction() {
		return transaction;
	}
	
	@Override
	public void setTransaction(Transaction t) {
		transaction = t;
	}

}
