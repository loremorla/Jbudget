package it.unicam.cs.pa.jbudget105124.Controller;

public interface ControllerManager {
    static Controller createController(){
        return new SimpleController();
    }
}
