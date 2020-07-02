package it.unicam.cs.pa.jbudget105124;

import it.unicam.cs.pa.jbudget105124.Controller.Controller;
import it.unicam.cs.pa.jbudget105124.Controller.ControllerManager;
import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Account.AccountBasic;
import it.unicam.cs.pa.jbudget105124.Model.Account.AccountType;
import it.unicam.cs.pa.jbudget105124.Model.Movement.MovementBasic;
import it.unicam.cs.pa.jbudget105124.Model.Movement.MovementType;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Tag.TagSingle;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.TransactionBasic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller controller;
    private Transaction tr1;
    private Transaction tr2;
    private Account cassa;
    private Account cartaDebito;
    private Tag vacanza;
    private Tag trasporto;
    private Movement m1;
    private Movement m2;
    private Movement m3;
    private Movement m4;

    @BeforeEach
    void createController(){
        controller = ControllerManager.createController();
        tr1 = new TransactionBasic(1,"provaTransazione",LocalDate.of(2019,1,20));
        tr2 = new TransactionBasic(2,"TransizioneProva",LocalDate.of(2020,10,20));
        cassa = new AccountBasic(1,"cassa","prova1",10000.00,AccountType.ASSETS);
        cartaDebito = new AccountBasic(2,"cartaDebito","prova2",500.00,AccountType.LIABILITIES);
        vacanza = new TagSingle("vacanza",1);
        trasporto = new TagSingle("trasporto",2);
        m1 = new MovementBasic(1,200.00,"appartamento",cassa,vacanza,MovementType.DEBIT,tr1);
        m2 = new MovementBasic(2,55.00,"prestito benzina",cassa,trasporto,MovementType.CREDITS,tr2);
        m3 = new MovementBasic(3,150.00,"aereo",cartaDebito,trasporto,MovementType.DEBIT,tr2);
        m4 = new MovementBasic(4,15.00,"spuntino",cartaDebito,vacanza,MovementType.CREDITS,tr1);
    }

    @Test
    void addTag() {
        assertFalse(controller.getTags().contains(vacanza));
        assertFalse(controller.getTags().contains(trasporto));
        controller.addTag(vacanza);
        controller.addTag(trasporto);
        assertTrue(controller.getTags().contains(vacanza));
        assertTrue(controller.getTags().contains(trasporto));
    }

    @Test
    void removeTag() {
        controller.addTag(vacanza);
        controller.addTag(trasporto);
        assertTrue(controller.getTags().contains(vacanza));
        assertTrue(controller.getTags().contains(trasporto));
        controller.removeTag(vacanza);
        controller.removeTag(trasporto);
        assertFalse(controller.getTags().contains(vacanza));
        assertFalse(this.controller.getTags().contains(trasporto));
    }

    @Test
    void addMovement() {
        assertFalse(controller.getMovements().contains(m1));
        assertFalse(controller.getMovements().contains(m2));
        assertFalse(controller.getMovements().contains(m3));
        assertFalse(controller.getMovements().contains(m4));
        controller.addMovement(m1);
        controller.addMovement(m2);
        controller.addMovement(m3);
        controller.addMovement(m4);
        assertTrue(tr1.getMovements().contains(m1));
        assertTrue(cassa.getMovements().contains(m2));
        assertTrue(vacanza.getMovements().contains(m4));
        assertTrue(controller.getMovements().contains(m3));
    }

    @Test
    void removeMovement() {
        controller.addMovement(m1);
        controller.addTransaction(tr1);
        controller.addTag(vacanza);
        controller.addAccount(cassa);
        assertTrue(tr1.getMovements().contains(m1));
        assertTrue(vacanza.getMovements().contains(m1));
        assertTrue(cassa.getMovements().contains(m1));
        this.controller.removeMovement(m1);
        assertFalse(tr1.getMovements().contains(m1));
        assertFalse(vacanza.getMovements().contains(m1));
        assertFalse(cassa.getMovements().contains(m1));
    }

    @Test
    void addTransaction() {
        assertFalse(controller.getTransactions().contains(tr1));
        assertFalse(controller.getTransactions().contains(tr2));
        controller.addTransaction(tr1);
        controller.addTransaction(tr2);
        assertTrue(controller.getTransactions().contains(tr1));
        assertTrue(controller.getTransactions().contains(tr2));
    }

    @Test
    void removeTransaction() {
        controller.addTransaction(tr1);
        controller.addTransaction(tr2);
        assertTrue(controller.getTransactions().contains(tr1));
        assertTrue(controller.getTransactions().contains(tr2));
        controller.removeTransaction(tr1);
        controller.removeTransaction(tr2);
        assertFalse(controller.getTransactions().contains(tr1));
        assertFalse(controller.getTransactions().contains(tr2));
    }

    @Test
    void addAccount() {
        assertFalse(controller.getAccounts().contains(cassa));
        assertFalse(controller.getAccounts().contains(cartaDebito));
        controller.addAccount(cassa);
        controller.addAccount(cartaDebito);
        assertTrue(controller.getAccounts().contains(cassa));
        assertTrue(controller.getAccounts().contains(cartaDebito));
    }

    @Test
    void removeAccount() {
        controller.addAccount(cassa);
        controller.addAccount(cartaDebito);
        assertTrue(controller.getAccounts().contains(cassa));
        assertTrue(controller.getAccounts().contains(cartaDebito));
        controller.removeAccount(cassa);
        controller.removeAccount(cartaDebito);
        assertFalse(controller.getAccounts().contains(cassa));
        assertFalse(controller.getAccounts().contains(cartaDebito));
    }

    @Test
    void scheduledTransactionDate() {
        List<Transaction> stransactions;
        controller.addTransaction(tr1);
        controller.addTransaction(tr2);
        stransactions = controller.scheduledTransactionsDate(LocalDate.of(2020,1,1),LocalDate.of(2021,1,20));
        assertFalse(stransactions.contains(tr1));
        assertTrue(stransactions.contains(tr2));
        stransactions = controller.scheduledTransactionsDate(LocalDate.of(2021,1,1),LocalDate.of(2022,1,20));
        assertFalse(stransactions.contains(tr1));
        assertFalse(stransactions.contains(tr2));
        stransactions = controller.scheduledTransactionsDate(LocalDate.of(2018,1,1),LocalDate.of(2019,1,1));
        assertFalse(stransactions.contains(tr1));
        assertFalse(stransactions.contains(tr2));
        stransactions = controller.scheduledTransactionsDate(LocalDate.of(2018,1,1),LocalDate.of(2022,1,1));
        assertTrue(stransactions.contains(tr1));
        assertTrue(stransactions.contains(tr2));
    }

    @Test
    void scheduledTransactionsTag() {
        List<Transaction> stransactions;
        Tag alimenti = new TagSingle("alimenti",3);
        controller.addTransaction(tr1);
        controller.addTransaction(tr2);
        controller.addMovement(m1);
        controller.addMovement(m2);
        stransactions = controller.scheduledTransactionsTag(alimenti);
        assertFalse(stransactions.contains(tr1));
        assertFalse(stransactions.contains(tr2));
        stransactions = controller.scheduledTransactionsTag(vacanza);
        assertTrue(stransactions.contains(tr1));
        assertFalse(stransactions.contains(tr2));
        stransactions = controller.scheduledTransactionsTag(trasporto);
        assertFalse(stransactions.contains(tr1));
        assertTrue(stransactions.contains(tr2));
    }

    @Test
    void addBudgetTag(){
        controller.addTag(vacanza);
        controller.addTag(trasporto);
        controller.addBudgetTag(vacanza, 10.0);
        assertEquals(controller.getBudgetReport().getBudget().getExpected(vacanza),10.0);
    }

    @Test
    void removeBudgetTag(){
        controller.addTag(vacanza);
        controller.addTag(trasporto);
        controller.addBudgetTag(vacanza, 10.0);
        assertEquals(controller.getBudgetReport().getBudget().getExpected(vacanza),10.0);
        controller.removeBudgetTag(vacanza);
        assertFalse(controller.getBudgetReport().getBudget().getBudget().containsKey(vacanza));
    }
}
