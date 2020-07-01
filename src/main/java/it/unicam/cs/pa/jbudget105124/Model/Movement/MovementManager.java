package it.unicam.cs.pa.jbudget105124.Model.Movement;

import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Account.AccountType;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;

import java.time.LocalDate;
import java.util.List;

/**
 * Interfaccia che ha il compito di creare un Movement.
 */
public interface MovementManager {

    /**
     * Metodo per generare un MovementBasic.
     * @return MovementBasic generato.
     */
    static Movement createMovement(int ID, double amount, String description, Account account,
                                   Tag tag,MovementType mt, Transaction t) {
        return new MovementBasic(ID, amount, description, account, tag, mt, t);
    }
}
