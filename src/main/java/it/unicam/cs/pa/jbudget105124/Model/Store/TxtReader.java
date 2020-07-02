package it.unicam.cs.pa.jbudget105124.Model.Store;

import it.unicam.cs.pa.jbudget105124.Model.BudgetReport.BudgetReport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Classe che ha il compito di leggere da un file txt.
 */
public class TxtReader implements Reader{

    /**
     * Oggetto per la lettura
     */
    private final ObjectInputStream in;

    /**
     * Costruttore di TxtReader
     * @param path
     * @throws IOException
     */
    public TxtReader(String path) throws IOException {
        in = new ObjectInputStream(new FileInputStream(path));
    }

    /**
     * Metodo per leggere un Budget Report
     * @return Budget Report letto
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public BudgetReport read() throws IOException, ClassNotFoundException {
        return (BudgetReport) in.readObject();
    }

    /**
     * Metodo per chiudere le variabili istanziate per leggere.
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        in.close();
    }
}
