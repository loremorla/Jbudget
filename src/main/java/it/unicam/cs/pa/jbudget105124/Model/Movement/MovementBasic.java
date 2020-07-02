package it.unicam.cs.pa.jbudget105124.Model.Movement;

import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
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
	private MovementType movementType;
	private Transaction transaction;

	
	public MovementBasic(int ID, double amount,String description,Account account,
			Tag tag,MovementType mt,Transaction t) {
		this.ID = ID;
		this.date = t.getDate();
		this.amount = amount;
		this.description = description;
		this.tag = tag;
		this.movementType = mt;
		this.transaction = t;
		this.account = account;
	}
	
	@Override
	public int getID() {
		return ID;
	}

	@Override
	public LocalDate getDate() {
		return date;
	}

	@Override
	public Tag getTag() {
		return tag;
	}

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
	public Account getAccount() {
		return account;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public MovementType getMovementType() {
		return movementType;
	}

	@Override
	public Transaction getTransaction() {
		return transaction;
	}

}
