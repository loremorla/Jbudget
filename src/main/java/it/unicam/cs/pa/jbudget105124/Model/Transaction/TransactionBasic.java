package it.unicam.cs.pa.jbudget105124.Model.Transaction;

import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;

import java.time.LocalDate;
import java.util.*;

public class TransactionBasic implements Transaction{
	
	private int ID;
	private List<Tag> tags;
	private LocalDate date;
	private List<Movement> movements;
	private double amount;
	private String description;
	
	public TransactionBasic(int ID,String description,LocalDate date) {
		this.ID = ID;
		movements = new ArrayList<>();
		tags = new ArrayList<>();
		this.date = date;
		this.description = description;
	}

	@Override
	public int getID() {
		return ID;
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
	public List<Tag> getTags() {
		movements.stream().forEach(m -> tags.add(m.getTag()));
		return tags;
	}

	@Override
	public LocalDate getDate() {
		return date;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public double getTotalAmount() {
		amount = 0.0;
		for(Movement m : this.getMovements()) {
			amount = amount + m.getRealAmount();
		}
		return amount;
	}
}
