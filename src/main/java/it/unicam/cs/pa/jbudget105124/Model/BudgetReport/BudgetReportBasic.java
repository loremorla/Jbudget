package it.unicam.cs.pa.jbudget105124.Model.BudgetReport;

import com.google.gson.Gson;
import it.unicam.cs.pa.jbudget105124.Model.Account.AccountType;
import it.unicam.cs.pa.jbudget105124.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105124.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105124.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105124.Model.Tag.Tag;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetReportBasic implements BudgetReport {
	
	private List<Tag> tags;
	private Budget budget;
	private Ledger ledger;
	//private Map<Tag,Double> effectiveBudget;
	private Map<Tag,Double> result;
	
	public BudgetReportBasic(Budget b, Ledger l) {
		this.tags = new ArrayList<>();
		result = new HashMap<>();
		budget = b;
		ledger = l;
		//this.effectiveBudget = effectiveBudget;
	}

	/*@Override
	public List<Tag> getTags() {
		return tags;
	}*/
	// ce se prova
	@Override
	public Map<Tag, Double> report() {
		result = new HashMap<>();
        /*budget.getTags().stream().filter(t->effectiveBudget.containsKey(t))
                .forEach(t->result.put(t,budget.getExpected(t)+effectiveBudget.get(t)));
        budget.getTags().stream().filter(t->!effectiveBudget.containsKey(t))
        		.forEach(t->result.put(t,budget.getExpected(t)));*/
		budget.getTags().stream()
				.forEach(t->result.put(t,ledger.getSingleTag(t.getID()).getTotalAmount()-budget.getExpected(t)));
		return result;
	}


	/*@Override
	public Map<Tag,Double> getEffectiveBudget() {
		return effectiveBudget;
	}*/

	@Override // ???
	public double getTag(Tag t) {
		return result.get(t);
	}

	@Override
	public double getEffectiveAmount(Tag t){
		double res = 0.0;
		for(Movement m : ledger.getMovements()){
			if(!m.getDate().isAfter(LocalDate.now())){
				res = res+m.getRealAmount();
			}
		}
		return res;
	}

	@Override
	public Ledger getLedger() {
		return this.ledger;
	}

	@Override
	public Budget getBudget() {
		return this.budget;
	}

}
