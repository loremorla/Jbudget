package it.unicam.cs.pa.jbudget105124.Model.Transaction;

import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;

import java.time.LocalDate;
import java.util.*;

/**
 * Classe che ha il compito di gestire una transazione, contenente più movimenti.
 */
public class TransactionBasic implements Transaction{

	/**
	 * ID della transazione
	 */
	private int ID;
	/**
	 * lista di tag della transazione
	 */
	private List<Tag> tags;
	/**
	 * data della transazione
	 */
	private LocalDate date;
	/**
	 * lista di movimenti della transazione
	 */
	private List<Movement> movements;
	/**
	 * amount della transazione
	 */
	private double amount;
	/**
	 * descrizione della transazione
	 */
	private String description;

	/**
	 * Costruttore di TransactionBasic
	 * @param ID
	 * @param description
	 * @param date
	 */
	public TransactionBasic(int ID,String description,LocalDate date) {
		this.ID = ID;
		movements = new ArrayList<>();
		tags = new ArrayList<>();
		this.date = date;
		this.description = description;
	}

	/**
	 * Metodo per ritornare l'ID della transazione
	 * @return
	 */
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * Metodo per ritornare la lista di movimenti della transazione
	 * @return lista di movimenti da ritornare
	 */
	@Override
	public List<Movement> getMovements() {
		return movements;
	}

	/**
	 * Metodo per aggiungere un movimento dalla lista di
	 * movimenti della transazione
	 * @param m movimento da aggiungere
	 */
	@Override
	public void addMovement(Movement m) {
		movements.add(m);
	}

	/**
	 * Metodo per rimuovere un movimento dalla lista di
	 * movimenti della transazione
	 * @param m movimento da rimuovere
	 */
	@Override
	public void removeMovement(Movement m) {
		movements.remove(m);
	}

	/**
	 * Metodo per ritornare la lista di tag
	 * @return lista di tag da ritornare
	 */
	@Override
	public List<Tag> getTags() {
		movements.stream().forEach(m -> tags.add(m.getTag()));
		return tags;
	}

	/**
	 * Metodo per ritornare la date della transazione
	 * @return data da ritornare
	 */
	@Override
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Metodo per ritornare la descrizione della transazione
	 * @return descrizione da ritornare
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * Metodo per ritornare l'amount totale della transazione
	 * @return amount da ritornare
	 */
	@Override
	public double getTotalAmount() {
		amount = 0.0;
		for(Movement m : this.getMovements()) {
			amount = amount + m.getRealAmount();
		}
		return amount;
	}
}
