package it.unicam.cs.pa.jbudget105124.Model.Store;

import it.unicam.cs.pa.jbudget105124.Model.BudgetReport.BudgetReport;

import java.io.IOException;

/**
 * Interfaccia che ha il compito di leggere da file.
 */
public interface Reader {

    /**
     * Metodo per leggere un Budget Report
     * @return Budget Report letto
     * @throws IOException
     * @throws ClassNotFoundException
     */
    BudgetReport read() throws IOException, ClassNotFoundException;

    /**
     * Metodo per chiudere le variabili istanziate per leggere.
     * @throws IOException
     */
    void close() throws IOException;
}
