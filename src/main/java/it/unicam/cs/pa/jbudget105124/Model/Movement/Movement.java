package it.unicam.cs.pa.jbudget105124.Model.Movement;

import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105124.Model.Utility;

import java.time.LocalDate;

/**
 * Interfaccia cha sarà implementata dalle classi che hanno il compito di gestire un movimento che
 * verrà associato ad una transazione, ad un account ed un tag.
 */
public interface Movement extends Utility {

	/**
	 * Metodo per ritornare l'ID del movimento
	 * @return ID movimento
	 */
	int getID();

	/**
	 * Metodo per ritornare la date del movimento
	 * @return data del movimento
	 */
	LocalDate getDate();

	/**
	 * Metodo per ritornare il tag del movimento
	 * @return tag del movimento
	 */
	Tag getTag();

	/**
	 * Metodo per ritornare il tipo di movimento
	 * @return tipo di movimento da ritornare
	 */
	MovementType getMovementType();

	/**
	 * Metodo per ritornare la transazione contenente il movimento
	 * @return transazione da ritornare
	 */
	Transaction getTransaction();

	/**
	 * Metodo per ritornare l'amount del movimento
	 * @return amount del movimento
	 */
	double getAmount();

	/**
	 * Metodo per ritornare il real amount del movimento
	 * in base al suo tipo
	 * @return amount reale del movimento
	 */
	double getRealAmount();

	/**
	 * Metodo per ritornare l'account associato al movimento
	 * @return account da ritornare
	 */
	Account getAccount();

	/**
	 * Metodo per ritornare la descrizione del movimento
	 * @return descrizione da ritornare
	 */
	String getDescription();
		
}
