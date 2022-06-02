package it.prova.pizzastore.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import it.prova.pizzastore.model.Pizza;

public class PizzaDAOImpl implements PizzaDAO{
	
	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		// TODO Auto-generated method stub
		this.entityManager = entityManager;
	}

	@Override
	public List<Pizza> list() throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createQuery("FROM Pizza", Pizza.class).getResultList();	
	}

	@Override
	public Optional<Pizza> findOne(Long id) throws Exception {
		// TODO Auto-generated method stub
		Pizza result = entityManager.find(Pizza.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Pizza input) throws Exception {
		// TODO Auto-generated method stub
		if(input == null)
			throw new Exception("Problema valore in input");
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Pizza input) throws Exception {
		// TODO Auto-generated method stub
		if(input == null)
			throw new Exception("Problema valore in input");
		entityManager.persist(input);
	}

	@Override
	public void delete(Pizza input) throws Exception {
		// TODO Auto-generated method stub
		if(input == null)
			throw new Exception("Problema valore in input");
		entityManager.remove(entityManager.merge(input));
	}

}
