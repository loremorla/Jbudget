package it.unicam.cs.pa.jbudget105124;

import it.unicam.cs.pa.jbudget105124.Model.Budget.BudgetTag;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105124.Model.Tag.TagSingle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetTest {

    private BudgetTag budget;
    private TagSingle vacanza;
    private TagSingle trasporto;

    @BeforeEach
    void createBudgetTag(){
        budget = new BudgetTag();
        vacanza = new TagSingle("vacanza",1);
        trasporto = new TagSingle("trasporto",2);
    }

    @Test
    void add() {
        assertTrue(budget.getBudget().isEmpty());
        budget.add(vacanza,150);
        assertFalse(budget.getBudget().isEmpty());
        assertTrue(budget.getBudget().containsKey(vacanza));
        assertEquals(budget.getBudget().get(vacanza),150);
    }

    @Test
    void remove() {
        budget.add(trasporto,222);
        assertFalse(budget.getBudget().isEmpty());
        budget.remove(trasporto);
        assertTrue(budget.getBudget().isEmpty());
    }

    @Test
    void getBudget() {
        Map<Tag,Double> budget2 = new HashMap<>();
        budget2.put(trasporto,160.00);
        budget.add(trasporto,160);
        assertEquals(budget2.get(trasporto),this.budget.getBudget().get(trasporto));
    }

    @Test
    void getTags() {
        this.budget.add(vacanza,1000);
        this.budget.add(trasporto,500);
        List<Tag> Tags = new ArrayList<>();
        Tags.add(vacanza);
        Tags.add(trasporto);
        //assertEquals(Tags,budget.getTags());
        budget.remove(vacanza);
        assertNotEquals(Tags,budget.getTags());
    }
}

