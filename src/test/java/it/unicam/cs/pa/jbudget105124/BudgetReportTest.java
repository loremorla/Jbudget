package it.unicam.cs.pa.jbudget105124;


import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Account.AccountBasic;
import it.unicam.cs.pa.jbudget105124.Model.Account.AccountType;
import it.unicam.cs.pa.jbudget105124.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105124.Model.Budget.BudgetTag;
import it.unicam.cs.pa.jbudget105124.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105124.Model.BudgetReport.BudgetReportBasic;
import it.unicam.cs.pa.jbudget105124.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105124.Model.Ledger.LedgerBasic;
import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Movement.MovementBasic;
import it.unicam.cs.pa.jbudget105124.Model.Movement.MovementType;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Tag.TagSingle;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.TransactionBasic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetReportTest {

    private Ledger ledger;
    private Budget budget;
    private BudgetReport budgetReport;

    @BeforeEach
    void createBudgetReportBase(){
        ledger = new LedgerBasic();
        budget = new BudgetTag();
        budgetReport = new BudgetReportBasic(budget,ledger);
    }

    @Test
    void getLedger() {
        assertEquals(ledger,budgetReport.getLedger());
        assertTrue(ledger instanceof Ledger);
    }

    @Test
    void getBudget() {
        assertEquals(budget,budgetReport.getBudget());
        assertTrue(budget instanceof Budget);
    }

    @Test
    void report() {

        Account cassa = new AccountBasic(1,"cassa","prova1",10000.00, AccountType.ASSETS);
        Tag vacanza = new TagSingle("vacanza",1);
        Tag trasporto = new TagSingle("trasporto",2);
        Transaction tr1 = new TransactionBasic(1,"provaTransazione", LocalDate.of(2019,1,20));
        Movement m1 = new MovementBasic(1,10.00,"appartamento",
                cassa,vacanza,MovementType.DEBIT,tr1);
        Movement m2 = new MovementBasic(2,5.00,"prestito benzina",
                cassa,trasporto,MovementType.CREDITS,tr1);
        vacanza.addMovement(m1);
        cassa.addMovement(m1);
        tr1.addMovement(m1);
        trasporto.addMovement(m2);
        cassa.addMovement(m2);
        tr1.addMovement(m2);
        budgetReport.getLedger().addAccount(cassa);
        budgetReport.getLedger().addTransaction(tr1);
        budgetReport.getLedger().addMovement(m1);
        budgetReport.getLedger().addMovement(m2);
        budgetReport.getLedger().addTag(vacanza);
        budgetReport.getLedger().addTag(trasporto);
        budgetReport.getBudget().add(vacanza,100.00);
        budgetReport.getBudget().add(trasporto,50.00);
        assertEquals(budgetReport.report().get(vacanza),m1.getRealAmount()-budget.getBudget().get(vacanza));
        assertEquals(budgetReport.report().get(trasporto),m2.getRealAmount()-budget.getBudget().get(trasporto));

    }

}
