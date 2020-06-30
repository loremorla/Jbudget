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
	
	public TagSingle() {
	
	}
	
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
	public void setName(String name) {
		this.name = name;
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
		return this.movements;
	}

	@Override
	public double getTotalAmount() {
		//AtomicReference<Double> amount = new AtomicReference<>();
		totalAmount = 0.0;
		//amount.set(0.0);
		/*this.movements.parallelStream()
				.filter(m->m.getDate().compareTo(LocalDate.now())<=0)
				.forEach(m->totalAmount+m.getAmount());
		//this.logger.finest("TotalAmount getter.");  amount.set(m.getAmount()+amount.get())
		return amount.get();*/
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

	public String toString(){
		return ID+"_"+name;
	}

}
