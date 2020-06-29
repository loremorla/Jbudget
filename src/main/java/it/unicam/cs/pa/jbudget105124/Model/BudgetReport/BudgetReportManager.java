package it.unicam.cs.pa.jbudget105124.Model.BudgetReport;

import it.unicam.cs.pa.jbudget105124.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105124.Model.Budget.Budget;

public interface BudgetReportManager {
    static BudgetReport createReport(Ledger ledger, Budget budget){
        return new BudgetReportBasic(budget,ledger);
    }
}
