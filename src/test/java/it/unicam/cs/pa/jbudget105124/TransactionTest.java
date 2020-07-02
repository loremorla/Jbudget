package it.unicam.cs.pa.jbudget105124;

import it.unicam.cs.pa.jbudget105124.Model.Account.Account;
import it.unicam.cs.pa.jbudget105124.Model.Account.AccountBasic;
import it.unicam.cs.pa.jbudget105124.Model.Account.AccountType;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {

    private Transaction transaction;
    private Transaction transaction2;
    private Account fondoCassa;
    private Tag sport;
    private Tag benzina;
    private Movement d1;
    private Movement d2;

    @BeforeEach
    void createTransactionBase(){
        transaction = new TransactionBasic(1,"provaTransazione", LocalDate.of(2019,1,20));
        transaction2 = new TransactionBasic(2,"TransizioneProva",LocalDate.of(2020,2,20));
        fondoCassa = new AccountBasic(1,"fondoCassa","prova1",10000.00, AccountType.ASSETS);
        sport = new TagSingle("sport",1);
        benzina = new TagSingle("benzina",2);
        d1 = new MovementBasic(1,200.00,"appartamento",fondoCassa,sport, MovementType.CREDITS,transaction);
        d2 = new MovementBasic(2,55.00,"prestito benzina",fondoCassa,benzina,MovementType.CREDITS,transaction);
    }

    @Test
    void getDescription(){
        assertEquals(transaction.getDescription(),"provaTransazione");
        assertEquals(transaction2.getDescription(),"TransizioneProva");
    }

    @Test
    void addMovement() {
        assertFalse(transaction2.getMovements().contains(d1));
        assertFalse(transaction2.getMovements().contains(d2));
        transaction2.addMovement(d1);
        transaction2.addMovement(d2);
        assertTrue(transaction2.getMovements().contains(d1));
        assertTrue(transaction2.getMovements().contains(d2));
    }

    @Test
    void removeMovement() {
        transaction2.addMovement(d1);
        transaction2.addMovement(d2);
        assertTrue(transaction2.getMovements().contains(d1));
        assertTrue(transaction2.getMovements().contains(d2));
        transaction2.removeMovement(d1);
        transaction2.removeMovement(d2);
        assertFalse(transaction2.getMovements().contains(d1));
        assertFalse(transaction2.getMovements().contains(d2));
    }

    @Test
    void getTags() {
        transaction.addMovement(d1);
        transaction.addMovement(d2);
        assertTrue(transaction.getTags().contains(sport));
        assertTrue(transaction.getTags().contains(benzina));
    }

    @Test
    void getDate() {
        assertTrue(transaction.getDate().equals(d1.getDate()));
        assertTrue(transaction.getDate().isBefore(LocalDate.now()));
    }

    @Test
    void getTotalAmount() {
        transaction.addMovement(d1);
        transaction.addMovement(d2);
        assertEquals(transaction.getTotalAmount(),d1.getRealAmount()+d2.getRealAmount());
    }

    @Test
    void getID() {
        assertNotEquals(transaction.getID(),2);
        assertNotEquals(transaction.getID(),-1);
        assertEquals(transaction.getID(),1);
    }

}
