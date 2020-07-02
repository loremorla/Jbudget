package it.unicam.cs.pa.jbudget105124;

import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Account.AccountBasic;
import it.unicam.cs.pa.jbudget105124.Model.Account.AccountType;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LedgerTest {

    private Ledger ledger;
    private Transaction tr;
    private Account banca;
    private Movement m;
    private Tag sport;

    @BeforeEach
    void createLedgerBase(){
        ledger = new LedgerBasic();
        sport = new TagSingle("sport",1);
        tr = new TransactionBasic(1,"provaTransazione", LocalDate.of(2019,1,20));
        banca = new AccountBasic(1,"banca","prova1",10000.00, AccountType.ASSETS);
        m = new MovementBasic(1,200.00,"appartamento",banca,sport, MovementType.DEBIT,tr);

    }

    @Test
    void addAccount() {
        assertFalse(ledger.getAccounts().contains(banca));
        ledger.addAccount(banca);
        assertTrue(ledger.getAccounts().contains(banca));
    }

    @Test
    void removeAccount() {
        assertFalse(ledger.getAccounts().contains(banca));
        ledger.addAccount(banca);
        assertTrue(ledger.getAccounts().contains(banca));
        ledger.removeAccount(banca);
        assertFalse(ledger.getAccounts().contains(banca));
    }

    @Test
    void getSingleAccount() {
        int id = banca.getID();
        assertNotEquals(ledger.getSingleAccount(id),banca);
        this.ledger.addAccount(banca);
        assertEquals(this.ledger.getSingleAccount(id),banca);
    }

    @Test
    void addTransaction() {
        assertFalse(ledger.getTransactions().contains(tr));
        ledger.addTransaction(tr);
        assertTrue(ledger.getTransactions().contains(tr));
    }

    @Test
    void removeTransaction() {
        assertFalse(ledger.getTransactions().contains(tr));
        ledger.addTransaction(tr);
        assertTrue(ledger.getTransactions().contains(tr));
        ledger.removeTransaction(tr);
        assertFalse(ledger.getTransactions().contains(tr));
    }

    @Test
    void getSingleTransaction() {
        int id = tr.getID();
        assertNotEquals(ledger.getSingleTransaction(id),tr);
        ledger.addTransaction(tr);
        assertEquals(ledger.getSingleTransaction(id),tr);
    }

    @Test
    void addMovement() {
        assertFalse(ledger.getMovements().contains(m));
        ledger.addMovement(m);
        assertTrue(ledger.getMovements().contains(m));
    }

    @Test
    void removeMovement() {
        assertFalse(ledger.getMovements().contains(m));
        ledger.addMovement(m);
        assertTrue(ledger.getMovements().contains(m));
        ledger.removeMovements(m);
        assertFalse(ledger.getMovements().contains(m));
    }

    @Test
    void addTag() {
        assertFalse(ledger.getTags().contains(sport));
        ledger.addTag(sport);
        assertTrue(ledger.getTags().contains(sport));
    }

    @Test
    void removeTag() {
        assertFalse(ledger.getTags().contains(sport));
        ledger.addTag(sport);
        assertTrue(ledger.getTags().contains(sport));
        ledger.removeTag(sport);
        assertFalse(ledger.getTags().contains(sport));
    }

    @Test
    void getSingleTag() {
        int id = sport.getID();
        assertNotEquals(this.ledger.getSingleTag(id),sport);
        this.ledger.addTag(sport);
        assertEquals(this.ledger.getSingleTag(id),sport);
    }

}
