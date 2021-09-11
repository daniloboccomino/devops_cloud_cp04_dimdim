package br.com.fiap.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.BankSlip;
import br.com.fiap.model.User;
import br.com.fiap.util.EntityManagerFacade;

public class BankSlipDAO {
	
	private EntityManager manager = new EntityManagerFacade().getEntityManager();

	public void save(BankSlip bankSlip) {
		manager.getTransaction().begin();
		manager.persist(bankSlip);
		manager.getTransaction().commit();
		manager.clear();
	}
	
	public List<BankSlip> getAll(){
		String jpql = "SELECT b FROM BankSlip b";
		TypedQuery<BankSlip> createQuery = manager.createQuery(jpql, BankSlip.class);
		return createQuery.getResultList();
	}

	public BankSlip findById(Long id) {
		return manager.find(BankSlip.class, id);
		
	}

	public void update(BankSlip bankSlip) {
		manager.getTransaction().begin();
		manager.merge(bankSlip);
		manager.flush();
		manager.getTransaction().commit();		
	}

	public void delete(BankSlip bankSlip) {
		manager.getTransaction().begin();
		manager.remove(bankSlip);
		manager.flush();
		manager.getTransaction().commit();
	}

	public List<BankSlip> getByUser(User user) {
		System.out.println(user.getId());
		String jpql = "SELECT b FROM BankSlip b WHERE user_id = :user_id";; 
		TypedQuery<BankSlip> query = manager.createQuery(jpql, BankSlip.class).setParameter("user_id", user.getId());
		List<BankSlip> resultList = query.getResultList();
		if (resultList == null || resultList.isEmpty()) {
			return Collections.emptyList();
		}
		return resultList;
	}
	
}
