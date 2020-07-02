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

public class MovementTest {

    private Transaction transaction1;
    private Transaction transaction2;
    private Account fondoCassa;
    private Account prepagata;
    private Tag sport;
    private Tag benzina;
    private Movement d1;
    private Movement d2;
    private Movement c1;

    @BeforeEach
    void createMovementBase(){

        transaction1 = new TransactionBasic(1,"provaTransazione",LocalDate.of(2019,1,20));
        transaction2 = new TransactionBasic(2,"TransizioneProva",LocalDate.of(2020,2,20));
        fondoCassa = new AccountBasic(1,"fondoCassa","prova1",10000.00,AccountType.ASSETS);
        prepagata = new AccountBasic(2,"prepagata","prova2",500.00,AccountType.LIABILITIES);
        sport = new TagSingle("sport",1);
        benzina = new TagSingle("benzina",2);
        d1 = new MovementBasic(1,200.00,"appartamento",
                fondoCassa,sport,MovementType.DEBIT,transaction1);
        d2 = new MovementBasic(2,55.00,"prestito benzina",
                fondoCassa,benzina,MovementType.DEBIT,transaction1);
        c1 = new MovementBasic(3,15.00,"spuntino",
                fondoCassa,sport,MovementType.CREDITS,transaction2);
    }

    @Test
    void getDescription() {
        assertEquals(c1.getDescription(),"spuntino");
        assertNotEquals(c1.getDescription(),"appartamento");
    }

    @Test
    void getType() {
        assertEquals(c1.getMovementType(),MovementType.CREDITS);
        assertNotEquals(c1.getMovementType(),MovementType.DEBIT);
        assertEquals(d1.getMovementType(),MovementType.DEBIT);
        assertNotEquals(d1.getMovementType(),MovementType.CREDITS);
        assertEquals(d2.getMovementType(),MovementType.DEBIT);
        assertNotEquals(d2.getMovementType(),MovementType.CREDITS);

    }

    @Test
    void getAmount() {
        assertEquals(c1.getAmount(),15.00);
        assertNotEquals(c1.getAmount(),d2.getAmount());
        assertEquals(d1.getAmount(),200.00);
        assertEquals(d2.getAmount(),55.00);
    }

    @Test
    void getTransaction() {
        assertEquals(d1.getTransaction(),transaction1);
        assertNotEquals(d1.getTransaction(),transaction2);
    }

    @Test
    void getAccount() {
        assertEquals(c1.getAccount(),fondoCassa);
        assertNotEquals(c1.getAccount(),prepagata);
    }

    @Test
    void getDate() {
        assertNotEquals(c1.getDate(),transaction1.getDate());
        assertEquals(c1.getDate(),transaction2.getDate());
    }

    @Test
    void getTag() {
        assertEquals(c1.getTag(),sport);
        assertEquals(d2.getTag(),benzina);
        assertNotEquals(d2.getTag(),sport);
    }

    @Test
    void getID() {
        assertNotEquals(c1.getID(),9);
        assertEquals(c1.getID(),3);
        assertNotEquals(c1.getID(),d2.getID());
    }
}
