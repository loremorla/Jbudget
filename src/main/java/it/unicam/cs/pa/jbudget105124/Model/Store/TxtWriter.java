package it.unicam.cs.pa.jbudget105124.Model.Store;

import it.unicam.cs.pa.jbudget105124.Model.BudgetReport.BudgetReport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Classe che ha il compito di scrivere su un file txt.
 */
public class TxtWriter implements Writer{

    /**
     * Oggetto per la scrittura
     */
    private ObjectOutputStream out;
    /**
     * path del file
     */
    private final String path;

    /**
     * Costruttore di TxtWriter
     * @param path
     */
    public TxtWriter(String path){
        this.path = path;
    }

    /**
     * Metodo per scrivere un Budget Report
     * @param object
     * @throws IOException
     */
    @Override
    public void write(BudgetReport object) throws IOException {
        out = new ObjectOutputStream(new FileOutputStream(path));
        out.writeObject(object);
        out.flush();
    }

    /**
     * Metodo per chiudere le variabili istanziate per scrivere.
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        out.close();
    }

}
