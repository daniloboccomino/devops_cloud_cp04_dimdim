package br.com.fiap.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.BankSlipDAO;
import br.com.fiap.model.BankSlip;
import br.com.fiap.model.User;
import br.com.fiap.util.BankSlipCodeGenerator;

@Named
@RequestScoped
public class BankSlipBean {
	
	private BankSlip bankSlip = new BankSlip();

	public void save() {
		User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		this.bankSlip.setUser(user);
		this.bankSlip.setCode(BankSlipCodeGenerator.generateCode());
		new BankSlipDAO().save(this.bankSlip);
		FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage("Boleto Bancário cadastro com sucesso"));
		System.out.println(this.bankSlip.getUser().getId());
	}
	
	public List<BankSlip> getBankSlips(){
		return new BankSlipDAO().getAll();
	}
	
	public List<BankSlip> getBankSlipsByUser(){
		User user = (User) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("user");
		System.out.println(user.getId());
		return new BankSlipDAO().getByUser(user);
	}

	public BankSlip getBankSlip() {
		return bankSlip;
	}

	public void setBankSlip(BankSlip bankSlip) {
		this.bankSlip = bankSlip;
	}

}
