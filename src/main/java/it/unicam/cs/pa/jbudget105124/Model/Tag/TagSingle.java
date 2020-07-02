package it.unicam.cs.pa.jbudget105124.Model.Tag;

import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TagSingle implements Tag {
	
	private int ID;
	private String name;
	private double totalAmount;
	private List<Movement> movements;

	
	public TagSingle(String name,int ID) {
		this.name = name;
		this.ID = ID;
		movements = new ArrayList<>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public List<Movement> getMovements() {
		return this.movements;
	}

	@Override
	public double getTotalAmount() {
		totalAmount = 0.0;
		for(Movement m : movements){
			if(!m.getDate().isAfter(LocalDate.now())){
				totalAmount = totalAmount+m.getRealAmount();
			}
		}
		return totalAmount;
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
