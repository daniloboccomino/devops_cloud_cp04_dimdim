package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fiap.model.User;
import br.com.fiap.util.EntityManagerFacade;

public class UserDAO {
	
	private EntityManager manager = new EntityManagerFacade().getEntityManager();

	public void save(User user) {
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();
		manager.clear();
	}
	
	public List<User> getAll(){
		String jpql = "SELECT u FROM User u";
		TypedQuery<User> createQuery = manager.createQuery(jpql, User.class);
		return createQuery.getResultList();
	}
	
	public User findById(Long id) {
		return manager.find(User.class, id);		
	}

	public User exist(User user) {
		TypedQuery<User> query = manager.createQuery("SELECT u FROM User u WHERE "
							+ "email=:email AND "
							+ "password=:password",
							User.class);
		query.setParameter("email", user.getEmail());
		query.setParameter("password", user.getPassword());
		
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void update(User user) {
		manager.getTransaction().begin();
		manager.merge(user);
		manager.flush();
		manager.getTransaction().commit();
	}

	public void delete(User user) {
		manager.getTransaction().begin();
		manager.remove(user);
		manager.flush();
		manager.getTransaction().commit();
	}
	
}
