package it.unicam.cs.pa.jbudget105124;

import it.unicam.cs.pa.jbudget105124.Model.Account.AccountBasic;
import it.unicam.cs.pa.jbudget105124.Model.Account.AccountType;
import it.unicam.cs.pa.jbudget105124.Model.Movement.MovementBasic;
import it.unicam.cs.pa.jbudget105124.Model.Movement.MovementType;
import it.unicam.cs.pa.jbudget105124.Model.Tag.TagSingle;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105124.Model.Transaction.TransactionBasic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {

    private AccountBasic cassa;
    private AccountBasic cartaDebito;
    private TagSingle vacanza;
    private Transaction tr1;
    private MovementBasic m1;
    private MovementBasic m2;
    private MovementBasic m3;

    @BeforeEach
    void createAccountBasic(){
        cassa = new AccountBasic(1,"cassa","prova1",10000.00, AccountType.ASSETS);
        cartaDebito = new AccountBasic(2,"cartaDebito","prova2",500.00,AccountType.LIABILITIES);
        vacanza = new TagSingle("vacanza",1);
        tr1 = new TransactionBasic(1,"provaTransazione", LocalDate.of(2019,1,20));
        m1 = new MovementBasic(1,200.00,"appartamento",cassa,vacanza, MovementType.DEBIT,tr1);
        m2 = new MovementBasic(2,55.00,"prestito benzina",cassa,vacanza,MovementType.CREDITS,tr1);
        m3 = new MovementBasic(3,150.00,"aereo",cartaDebito,vacanza,MovementType.DEBIT,tr1);
    }

    /*@Test
    void AccountBasicTest(){
        assertThrows(IllegalArgumentException.class,()->new AccountBasic(1,"","prova1",10000.00,
                AccountType.ASSETS));
    }*/
    @Test
    void getID() {
        assertEquals(cassa.getID(),1);
        assertNotEquals(cassa.getID(),2);
        assertEquals(cartaDebito.getID(),2);
        assertNotEquals(cartaDebito.getID(),1);
    }

    @Test
    void getName() {
        assertEquals(cassa.getName(),"cassa");
        assertEquals(cartaDebito.getName(),"cartaDebito");
    }

    @Test
    void getDescription() {
        assertEquals(cassa.getDescription(),"prova1");
        assertEquals(cartaDebito.getDescription(),"prova2");
    }

    @Test
    void getOpeningBalance() {
        assertEquals(cassa.getOpeningBalance(),10000.00);
        assertNotEquals(cassa.getOpeningBalance(),-1300.00);
        assertEquals(cartaDebito.getOpeningBalance(),500.00);
        assertNotEquals(cartaDebito.getOpeningBalance(),220.00);
    }

    @Test
    void getBalance() {
        assertEquals(cassa.getBalance(),cassa.getOpeningBalance());
        cassa.addMovement(m1);
        assertNotEquals(cassa.getBalance(),cassa.getOpeningBalance());
    }

    @Test
    void addMovement() {
        assertFalse(cassa.getMovements().contains(m1));
        cassa.addMovement(m1);
        assertTrue(cassa.getMovements().contains(m1));
    }

    @Test
    void removeMovement() {
        cassa.addMovement(m1);
        assertTrue(cassa.getMovements().contains(m1));
        cassa.removeMovement(m1);
        assertFalse(cassa.getMovements().contains(m1));
    }

    @Test
    void getMovements() {
        assertFalse(cassa.getMovements().contains(m1));
        assertFalse(cartaDebito.getMovements().contains(m1));
        assertFalse(cassa.getMovements().contains(m2));
        assertFalse(cartaDebito.getMovements().contains(m2));
        cassa.addMovement(m1);
        cassa.addMovement(m2);
        cartaDebito.addMovement(m1);
        cartaDebito.addMovement(m2);
        assertTrue(cassa.getMovements().contains(m1));
        assertTrue(cartaDebito.getMovements().contains(m1));
        assertTrue(cassa.getMovements().contains(m2));
        assertTrue(cartaDebito.getMovements().contains(m2));

    }

    @Test
    void getAccountType() {
        assertEquals(cassa.getAccountType(),AccountType.ASSETS);
        assertNotEquals(cartaDebito.getAccountType(),AccountType.ASSETS);
        assertNotEquals(cassa.getAccountType(),AccountType.LIABILITIES);
        assertEquals(cartaDebito.getAccountType(),AccountType.LIABILITIES);
    }
}
