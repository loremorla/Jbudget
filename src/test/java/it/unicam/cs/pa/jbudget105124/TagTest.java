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

public class TagTest {

    private Tag cure;
    private Tag macchina;
    private Transaction transaction;
    private Account fondoCassa;
    private Account prepagata;
    private Movement c1;

    @BeforeEach
    void createTagBase(){
        cure = new TagSingle("cure",1);
        macchina = new TagSingle("macchina",2);
        transaction = new TransactionBasic(1,"provaTransazione", LocalDate.of(2019,1,20));
        fondoCassa = new AccountBasic(1,"cassa","prova1",10000.00, AccountType.ASSETS);
        prepagata = new AccountBasic(2,"cartaDebito","prova2",500.00,AccountType.LIABILITIES);
        c1 = new MovementBasic(1,200.00,"volvo",prepagata,
                macchina, MovementType.CREDITS,transaction);
    }

    @Test
    void getName() {
        assertTrue(cure.getName() instanceof String);
        assertEquals(cure.getName(),"cure");
        assertEquals(macchina.getName(),"macchina");
    }

    @Test
    void getID() {
        assertEquals(cure.getID(),1);
        assertNotEquals(cure.getID(),macchina.getID());
    }

    @Test
    void getMovements(){
        assertFalse(cure.getMovements().contains(c1));
        cure.addMovement(c1);
        assertTrue(cure.getMovements().contains(c1));
    }

    @Test
    void totalAmount(){
        cure.addMovement(c1);
        assertEquals(cure.getTotalAmount(),c1.getRealAmount());
    }

    @Test
    void addMovement(){
        assertFalse(cure.getMovements().contains(c1));
        cure.addMovement(c1);
        assertTrue(cure.getMovements().contains(c1));
    }

    @Test
    void removeMovement(){
        cure.addMovement(c1);
        assertTrue(cure.getMovements().contains(c1));
        cure.removeMovement(c1);
        assertFalse(cure.getMovements().contains(c1));
    }
}
