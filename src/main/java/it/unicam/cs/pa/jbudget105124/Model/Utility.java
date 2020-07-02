package it.unicam.cs.pa.jbudget105124.Model;

import java.io.Serializable;

/**
 * Interfaccia che ha il compito di garantire alle classi che la implementano
 * metodi per accedere e modificare alcuni campi specifici.
 */
public interface Utility extends Serializable {

    /**
     * Metodo per ritornare l'ID dell'oggetto
     * @return ID oggetto
     */
    int getID();

}
