package it.unicam.cs.pa.jbudget105124.Controller;

/**
 * Interfaccia che ha il compito di creare un Controller.
 */
public interface ControllerManager {

    /**
     * Metodo per generare un SimpleController.
     * @return SimpleController generato.
     */
    static Controller createController(){
        return new SimpleController();
    }
}
