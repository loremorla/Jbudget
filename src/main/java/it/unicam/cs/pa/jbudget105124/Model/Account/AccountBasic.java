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
	
	public AccountBasic() {
		this.movements = new ArrayList<>();
	}
	
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
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public void setDescription(String d) {
		description = d;
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
	public AccountType getAccountType() {
		return type;
	}
	
	@Override
	public void setAccountType(AccountType at) {
		type = at;
	}
	
	@Override
	public double getOpeningBalance() {
		return openingBalance;
	}
	
	@Override
	public void setOpeningBalance(double ob) {
		openingBalance = ob;
	}

	@Override
	public double getBalance() {
		balance = openingBalance;
		//amount.set(0.0);
		/*this.movements.parallelStream()
				.filter(m->m.getDate().compareTo(LocalDate.now())<=0)
				.forEach(m->totalAmount+m.getAmount());
		//this.logger.finest("TotalAmount getter.");  amount.set(m.getAmount()+amount.get())
		return amount.get();*/
		for(Movement m : movements){
			if(m.getDate().compareTo(LocalDate.now())<=0){
				if(type.equals(AccountType.ASSETS)) balance = balance+m.getRealAmount();
				else balance = balance-m.getRealAmount();
			}
		}
		return balance;
	}
	
	@Override
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public void incrementBalance(double amount) {
		balance = balance + amount;
	}

	@Override
	public void decrementBalance(double amount) {
		balance = balance - amount;
		
	}

	@Override
	public List<Movement> getMovements() {
		return movements;
	}
	
	@Override
	public void setMovements(List<Movement> m) {
		movements = m;
	}

	@Override
	public void addMovement(Movement m) {
		movements.add(m);
	}
	
	@Override
	public void removeMovement(Movement m) {
		movements.remove(m);
	}
	/*@Override
	public List<Movement> getMovements(Predicate<Movement> p) {
		return null;
	}*/
	public String toString(){
		return ID+"_"+name;
	}

}
