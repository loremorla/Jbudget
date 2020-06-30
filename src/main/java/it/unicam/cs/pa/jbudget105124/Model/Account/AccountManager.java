package it.unicam.cs.pa.jbudget105124.Model.Account;

/**
 * Interfaccia che ha il compito di creare un Account.
 */
public interface AccountManager {

    /**
     * Metodo per generare un AccountBasic.
     * @return AccountBasic generato.
     */
    static Account createAccount(int ID,String name,String description,double ob,AccountType at){
        return new AccountBasic(ID,name,description,ob,at);
    }
}
