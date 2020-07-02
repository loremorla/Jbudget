package it.unicam.cs.pa.jbudget105124.Model.Account;

import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountBasic implements Account {

	private int ID;
	private AccountType type;
	private String name;
	private String description;
	private double balance;
	private double openingBalance;
	private List<Movement> movements;
	
	public AccountBasic(int ID, String name, String description, double ob, AccountType at){
		this.ID = ID;
		this.name = name;
		this.description = description;
		this.balance = ob;
		this.type = at;
		this.openingBalance = ob;
		this.movements = new ArrayList<>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public int getID() {
		return ID;
	}
	
	@Override
	public AccountType getAccountType() {
		return type;
	}
	
	@Override
	public double getOpeningBalance() {
		return openingBalance;
	}

	@Override
	public double getBalance() {
		balance = openingBalance;
		for(Movement m : movements){
			if(!m.getDate().isAfter(LocalDate.now())){
				if(type.equals(AccountType.ASSETS)) balance = balance+m.getRealAmount();
				else balance = balance-m.getRealAmount();
			}
		}
		return balance;
	}

	@Override
	public List<Movement> getMovements() {
		return movements;
	}

	@Override
	public void addMovement(Movement m) {
		movements.add(m);
	}
	
	@Override
	public void removeMovement(Movement m) {
		movements.remove(m);
	}

	@Override
	public String toString(){
		return ID+" "+name;
	}

}
