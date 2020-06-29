package it.unicam.cs.pa.jbudget105124.Model.Store;

import it.unicam.cs.pa.jbudget105124.Model.BudgetReport.BudgetReport;

import java.io.IOException;

public interface Writer {
    void write(BudgetReport object) throws IOException;
    void close() throws IOException;
}
