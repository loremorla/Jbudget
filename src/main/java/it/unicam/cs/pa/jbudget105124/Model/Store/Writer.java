package it.unicam.cs.pa.jbudget105124.Model.Store;

import it.unicam.cs.pa.jbudget105124.Model.BudgetReport.BudgetReport;

import java.io.IOException;

/**
 * Interfaccia che ha il compito di scrivere su file.
 */
public interface Writer {

    /**
     * Metodo per scrivere un Budget Report
     * @param object
     * @throws IOException
     */
    void write(BudgetReport object) throws IOException;

    /**
     * Metodo per chiudere le variabili istanziate per scrivere.
     * @throws IOException
     */
    void close() throws IOException;
}
