package it.unicam.cs.pa.jbudget105124.Model.BudgetReport;

import it.unicam.cs.pa.jbudget105124.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105124.Model.Budget.Budget;

/**
 * Interfaccia che ha il compito di creare un Budget Report.
 */
public interface BudgetReportManager {

    /**
     * Metodo per generare un BudgetReportBasic.
     * @return BudgetReportBasic generato.
     */
    static BudgetReport createReport(Ledger ledger, Budget budget){
        return new BudgetReportBasic(budget,ledger);
    }
}
