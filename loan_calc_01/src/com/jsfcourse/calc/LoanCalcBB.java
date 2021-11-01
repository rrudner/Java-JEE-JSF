package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Random;

@Named
@RequestScoped
//@SessionScoped
public class LoanCalcBB {
	
	private Random rand = new Random();

	
	private Double amount = Double.valueOf(1000 * (rand.nextInt(10)+1));
	private Double year = Double.valueOf(1 * (rand.nextInt(5)+1));
	private Double interest = Double.valueOf(1 * (rand.nextInt(200)+1));
	private Double result;

	
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getYear() {
		return year;
	}
	public void setYear(Double year) {
		this.year = year;
	}
	public Double getInterest() {
		return interest;
	}
	public void setInterest(Double interest) {
		this.interest = interest;
	}
	public Double getResult() {
		return result;
	}
	public void setResult(Double result) {
		this.result = result;
	}

	@Inject
	FacesContext ctx;



	public boolean doTheMath() {
		try {

			result = ((interest / 100 * amount) + amount) / (year * 12);
			
			result = Math.round(result*100.0)/100.0;

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d podczas przetwarzania parametrów", null));
			return false;
		}
	}

	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}

	// Put result in messages on AJAX call
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Miesiêczna rata:: " + result, null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}
