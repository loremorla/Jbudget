package it.unicam.cs.pa.jbudget105124.Model.Store;

import it.unicam.cs.pa.jbudget105124.Model.BudgetReport.BudgetReport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TxtReader implements Reader{

    private final ObjectInputStream in;

    public TxtReader(String path) throws IOException {
        in = new ObjectInputStream(new FileInputStream(path));
    }

    @Override
    public BudgetReport read() throws IOException, ClassNotFoundException {
        return (BudgetReport) in.readObject();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
