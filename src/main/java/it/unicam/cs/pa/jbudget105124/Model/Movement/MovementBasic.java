package it.unicam.cs.pa.jbudget105124.Model.Movement;

import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;

import java.time.LocalDate;

/**
 * Classe che il compito di gestire un movimento.
 */
public class MovementBasic implements Movement {

	/**
	 * ID del movimento
	 */
	private int ID;
	/**
	 * data del movimento
	 */
	private LocalDate date;
	/**
	 * amount del movimento
	 */
	private double amount;
	/**
	 * descrizione del movimento
	 */
	private String description;
	/**
	 * tag del movimento
	 */
	private Tag tag;
	/**
	 * account del movimento
	 */
	private Account account;
	/**
	 * tipo del movimento
	 */
	private MovementType movementType;
	/**
	 * transazione in cui si trova il movimento
	 */
	private Transaction transaction;

	/**
	 * Costruttore di MovementBasic
	 * @param ID
	 * @param amount
	 * @param description
	 * @param account
	 * @param tag
	 * @param mt
	 * @param t
	 */
	public MovementBasic(int ID, double amount,String description,Account account,
			Tag tag,MovementType mt,Transaction t) {
		this.ID = ID;
		this.date = t.getDate();
		this.amount = amount;
		this.description = description;
		this.tag = tag;
		this.movementType = mt;
		this.transaction = t;
		this.account = account;
	}

	/**
	 * Metodo per ritornare l'ID del movimento
	 * @return ID movimento
	 */
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * Metodo per ritornare la date del movimento
	 * @return data del movimento
	 */
	@Override
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Metodo per ritornare il tag del movimento
	 * @return tag del movimento
	 */
	@Override
	public Tag getTag() {
		return tag;
	}

	/**
	 * Metodo per ritornare l'amount del movimento
	 * @return amount del movimento
	 */
	@Override
	public double getAmount() {
		return amount;
	}

	/**
	 * Metodo per ritornare il real amount del movimento
	 * in base al suo tipo
	 * @return amount reale del movimento
	 */
	@Override
	public double getRealAmount() {
		if(movementType.equals(MovementType.DEBIT)){
			return -amount;
		}
		else return amount;
	}

	/**
	 * Metodo per ritornare l'account associato al movimento
	 * @return account da ritornare
	 */
	@Override
	public Account getAccount() {
		return account;
	}

	/**
	 * Metodo per ritornare la descrizione del movimento
	 * @return descrizione da ritornare
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * Metodo per ritornare il tipo di movimento
	 * @return tipo di movimento da ritornare
	 */
	@Override
	public MovementType getMovementType() {
		return movementType;
	}

	/**
	 * Metodo per ritornare la transazione contenente il movimento
	 * @return transazione da ritornare
	 */
	@Override
	public Transaction getTransaction() {
		return transaction;
	}

}
