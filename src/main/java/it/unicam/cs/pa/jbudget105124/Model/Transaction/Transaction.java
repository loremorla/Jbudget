package it.unicam.cs.pa.jbudget105124.Model.Transaction;

import it.unicam.cs.pa.jbudget105124.Model.Utility;
import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;

import java.time.LocalDate;
import java.util.List;

/**
 * Interfaccia che sarà implementata dalle classi che hanno il compito di gestire una transazione.
 * Una transazione rappresenta un gruppo di movimenti ai quali eridita la data. L'insieme dei Tag
 * della transazione è dato dall'insieme di tutti i tag dei suoi movimenti
 */
public interface Transaction extends Utility {

	/**
	 * Metodo per ritornare l'ID della transazione
	 * @return
	 */
	int getID();

	/**
	 * Metodo per ritornare la lista di movimenti della transazione
	 * @return lista di movimenti da ritornare
	 */
	List<Movement> getMovements();

	/**
	 * Metodo per aggiungere un movimento dalla lista di
	 * movimenti della transazione
	 * @param m movimento da aggiungere
	 */
	void addMovement(Movement m);

	/**
	 * Metodo per rimuovere un movimento dalla lista di
	 * movimenti della transazione
	 * @param m movimento da rimuovere
	 */
	void removeMovement(Movement m);

	/**
	 * Metodo per ritornare la lista di tag
	 * @return lista di tag da ritornare
	 */
	List<Tag> getTags();

	/**
	 * Metodo per ritornare la date della transazione
	 * @return data da ritornare
	 */
	LocalDate getDate();

	/**
	 * Metodo per ritornare la descrizione della transazione
	 * @return descrizione da ritornare
	 */
	String getDescription();

	/**
	 * Metodo per ritornare l'amount totale della transazione
	 * @return amount da ritornare
	 */
	double getTotalAmount();
}
