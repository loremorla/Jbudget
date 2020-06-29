package it.unicam.cs.pa.jbudget105124.Model.Store;

import it.unicam.cs.pa.jbudget105124.Model.BudgetReport.BudgetReport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TxtWriter implements Writer{

    private ObjectOutputStream out;
    private final String path;

    public TxtWriter(String path) throws IOException {
        this.path = path;
    }

    @Override
    public void write(BudgetReport object) throws IOException {
        out = new ObjectOutputStream(new FileOutputStream(path));
        out.writeObject(object);
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }

}
