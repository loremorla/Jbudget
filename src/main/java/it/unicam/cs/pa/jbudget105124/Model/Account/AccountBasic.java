package it.unicam.cs.pa.jbudget105124.Model.Account;

import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che ha il compito di gestire un account.
 */
public class AccountBasic implements Account {

	/**
	 * ID dell'account
	 */
	private int ID;
	/**
	 * AccountType dell'account
	 */
	private AccountType type;
	/**
	 * nome dell'account
	 */
	private String name;
	/**
	 * descrizione dell'account
	 */
	private String description;
	/**
	 * bilancio dell'account
	 */
	private double balance;
	/**
	 * bilancio d'apertura dell'account
	 */
	private double openingBalance;
	/**
	 * lista di movimenti dell'account
	 */
	private List<Movement> movements;

	/**
	 * Costruttore di AccountBasic
	 * @param ID
	 * @param name
	 * @param description
	 * @param ob
	 * @param at
	 */
	public AccountBasic(int ID, String name, String description, double ob, AccountType at){
		this.ID = ID;
		this.name = name;
		this.description = description;
		this.balance = ob;
		this.type = at;
		this.openingBalance = ob;
		this.movements = new ArrayList<>();
	}

	/**
	 * Metodo per ritornare il nome dell'account
	 * @return nome account
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Metodo per ritornare la descrizione dell'account
	 * @return descrizione account
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * Metodo per ritornare l'ID dell'account
	 * @return ID account
	 */
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * Metodo per ritornare il tipo dell'account
	 * @return tipo dell'account
	 */
	@Override
	public AccountType getAccountType() {
		return type;
	}

	/**
	 * Metodo per ottenere il bilancio di apertura dell'account
	 * @return bilancio apertura account
	 */
	@Override
	public double getOpeningBalance() {
		return openingBalance;
	}

	/**
	 * Metodo per ottenere il bilancio dell'account
	 * @return bilancio dell'account
	 */
	@Override
	public double getBalance() {
		balance = openingBalance;
		for(Movement m : movements){
			if(!m.getDate().isAfter(LocalDate.now())){
				if(type.equals(AccountType.ASSETS)) balance = balance+m.getRealAmount();
				else balance = balance-m.getRealAmount();
			}
		}
		return balance;
	}

	/**
	 * Metodo per ritornare la lista di movimenti
	 * @return lista di movimenti
	 */
	@Override
	public List<Movement> getMovements() {
		return movements;
	}

	/**
	 * Metodo per aggiungere un movimento all'account
	 * @param m movimento da aggiungere
	 */
	@Override
	public void addMovement(Movement m) {
		movements.add(m);
	}

	/**
	 * Metodo per rimuovere un movimento all'account
	 * @param m movimento da rimuovere
	 */
	@Override
	public void removeMovement(Movement m) {
		movements.remove(m);
	}

	/**
	 * Metodo per ritornare l'oggetto Account in forma
	 * di stringa
	 * @return stringa dell'oggetto
	 */
	@Override
	public String toString(){
		return ID+" "+name;
	}

}
