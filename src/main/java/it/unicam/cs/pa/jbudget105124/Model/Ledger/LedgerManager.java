package it.unicam.cs.pa.jbudget105124.Model.Ledger;

public interface LedgerManager {
    static Ledger createLedger(){
        return new LedgerBasic();
    }
}
