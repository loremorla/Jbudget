package it.unicam.cs.pa.jbudget105124.Model.Transaction;

import java.time.LocalDate;

public interface TransactionManager {
    static Transaction createTransaction(int ID,String description,LocalDate date){
        return new TransactionBasic(ID,description,date);
    }
}
