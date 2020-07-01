package it.unicam.cs.pa.jbudget105124.Model.Transaction;

import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Movement.MovementType;

import java.time.LocalDate;
import java.util.*;

public class TransactionBasic implements Transaction{
	
	private int ID;
	private List<Tag> tags;
	private LocalDate date;
	private List<Movement> movements;
	//private boolean state;
	private double amount;
	private String description;
	
	public TransactionBasic(int ID,String description,LocalDate date) {
		this.ID = ID;
		movements = new ArrayList<>();
		tags = new ArrayList<>();
		//this.state = false;
		//this.amount = 0.0;
		this.date = date;//LocalDate.now();
		this.description = description;
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
	public void addMovements(List<Movement> ms){
		movements.addAll(ms);
	}

	@Override
	public void removeMovement(Movement m) {
		movements.remove(m);
	}

	@Override
	public List<Tag> getTags() {
		movements.stream().forEach(m -> tags.add(m.getTag()));
		return tags;
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
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}
	
	/*@Override
	public boolean getState() {
		return state;
	}
	
	@Override
	public void setState(boolean state) {
		this.state = state;
	}
	
	@Override
	public void completed() {
		state = true;
	}*/
	
	@Override
	public void addAmount(double amount) {
		this.amount = this.amount + amount;
	}
	
	@Override
	public void decrementAmount(double amount) {
		this.amount = this.amount - amount;
	}

	@Override
	public double getTotalAmount() {
		amount = 0.0;
		for(Movement m : this.getMovements()) {
				addAmount(m.getRealAmount());
		}
		return amount;
	}
}
