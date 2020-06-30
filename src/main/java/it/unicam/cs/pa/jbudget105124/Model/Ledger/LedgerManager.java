package it.unicam.cs.pa.jbudget105124.Model.Ledger;

/**
 * Interfaccia che ha il compito di creare un Ledger.
 */
public interface LedgerManager {

    /**
     * Metodo per generare un LedgerBasic.
     * @return LedgerBasic generato.
     */
    static Ledger createLedger(){
        return new LedgerBasic();
    }
}
