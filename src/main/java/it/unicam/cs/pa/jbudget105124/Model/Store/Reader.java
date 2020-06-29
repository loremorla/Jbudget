package it.unicam.cs.pa.jbudget105124.Model.Store;

import it.unicam.cs.pa.jbudget105124.Model.BudgetReport.BudgetReport;

import java.io.IOException;

public interface Reader {
    BudgetReport read() throws IOException, ClassNotFoundException;
    void close() throws IOException;
}
