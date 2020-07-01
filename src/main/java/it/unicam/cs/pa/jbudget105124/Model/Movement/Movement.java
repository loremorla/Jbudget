package it.unicam.cs.pa.jbudget105124.Model.Movement;

import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Account.AccountType;
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
	 * Metodo per impostare l'ID del movimento
	 * @param ID ID da impostare
	 */
	void setID(int ID);

	/**
	 * Metodo per ritornare la date del movimento
	 * @return data del movimento
	 */
	LocalDate getDate();

	/**
	 * Metodo per impostare la data del movimento
	 * @param date da impostare
	 */
	void setDate(LocalDate date);

	/**
	 * Metodo per ritornare il tag del movimento
	 * @return tag del movimento
	 */
	Tag getTag();

	/**
	 * Metodo per impostare il tag del movimento
	 * @param t
	 */
	void setTag(Tag t);

	/**
	 * Metodo per ritornare il tipo di movimento
	 * @return tipo di movimento da ritornare
	 */
	MovementType getMovementType();

	/**
	 * Metodo per impostare il tipo di movimento
	 * @param mt tipo di movimento da impostare
	 */
	void setMovementType(MovementType mt);

	/**
	 * Metodo per ritornare la transazione contenente il movimento
	 * @return transazione da ritornare
	 */
	Transaction getTransaction();

	/**
	 * Metodo per impostare la transazione contenente il movimento
	 * @param t transazione da impostare
	 */
	void setTransaction(Transaction t);

	//AccountType getAccountType();

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
	 * Metodo per impostare l'amount del movimento
	 * @param amount amount da impostare
	 */
	void setAmount(double amount);

	/**
	 * Metodo per ritornare l'account associato al movimento
	 * @return account da ritornare
	 */
	Account getAccount();

	/**
	 * Metodo per impostare l'account del movimento
	 * @param a account da impostare
	 */
	void setAccount(Account a);

	/**
	 * Metodo per ritornare la descrizione del movimento
	 * @return descrizione da ritornare
	 */
	String getDescription();

	/**
	 * Metodo per impostare la descrizione del movimento
	 * @param description descrizione da impostare
	 */
	void setDescription(String description);
		
}
