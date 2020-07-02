package it.unicam.cs.pa.jbudget105124.Model.Tag;

import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che ha il compito di gestire un tag.
 */
public class TagSingle implements Tag {

	/**
	 * ID del tag
	 */
	private int ID;
	/**
	 * nome del tag
	 */
	private String name;
	/**
	 * amount totale del tag
	 */
	private double totalAmount;
	/**
	 * lista di movimenti associati al tag
	 */
	private List<Movement> movements;

	/**
	 * Costruttore di TagSingle
	 * @param name
	 * @param ID
	 */
	public TagSingle(String name,int ID) {
		this.name = name;
		this.ID = ID;
		movements = new ArrayList<>();
	}

	/**
	 * Metodo per ritornare il nome del Tag
	 * @return nome da ritornare
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Metodo per ritornare l'ID del Tag
	 * @return ID da ritornare
	 */
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * Metodo per ritornare la lista di movimeni del Tag
	 * @return lista di movimenti
	 */
	@Override
	public List<Movement> getMovements() {
		return this.movements;
	}

	/**
	 * Metodo per ritornare l'amount totale riguardante il tag
	 * @return amount totale
	 */
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

	/**
	 * Metodo per aggiungere un movimento alla lista di
	 * movimenti del Tag
	 * @param m movimento da aggiungere
	 */
	@Override
	public void addMovement(Movement m) {
		movements.add(m);
	}

	/**
	 * Metodo per rimuovere un movimento dalla lista di
	 * movimenti del Tag
	 * @param m movimento da rimuovere
	 */
	@Override
	public void removeMovement(Movement m) {
		movements.remove(m);
	}

	/**
	 * Metodo per ritornare l'oggetto Tag in forma
	 * di stringa
	 * @return stringa dell'oggetto
	 */
	@Override
	public String toString(){
		return ID+" "+name;
	}

}
