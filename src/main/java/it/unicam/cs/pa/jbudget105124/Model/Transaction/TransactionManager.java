package it.unicam.cs.pa.jbudget105124.Model.Transaction;

import java.time.LocalDate;

/**
 * Interfaccia che ha il compito di creare una Transazione.
 */
public interface TransactionManager {

    /**
     * Metodo per generare una TransactionBasic
     * @param ID
     * @param description
     * @param date
     * @return TransactionBasic generata
     */
    static Transaction createTransaction(int ID,String description,LocalDate date){
        return new TransactionBasic(ID,description,date);
    }
}
