package it.prova.pizzastore.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.model.StatoUtente;
import it.prova.pizzastore.model.Utente;

public class UtenteDAOImpl implements UtenteDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		// TODO Auto-generated method stub
		this.entityManager = entityManager;
	}

	@Override
	public List<Utente> list() throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createQuery("FROM Utente", Utente.class).getResultList();
	}

	@Override
	public Optional<Utente> findOne(Long id) throws Exception {
		// TODO Auto-generated method stub
		Utente result = entityManager.find(Utente.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Utente input) throws Exception {
		// TODO Auto-generated method stub
		if (input == null)
			throw new Exception("Problema valore in input");
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Utente input) throws Exception {
		// TODO Auto-generated method stub
		if (input == null)
			throw new Exception("Problema valore in input");
		entityManager.persist(input);
	}

	@Override
	public void delete(Utente input) throws Exception {
		// TODO Auto-generated method stub
		if (input == null)
			throw new Exception("Problema valore in input");
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public List<Utente> findAllByRuolo(Ruolo ruoloInput) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Utente> query = entityManager
				.createQuery("SELECT u FROM Utente u JOIN u.ruoli r WHERE r = :ruoloInput", Utente.class);
		query.setParameter("ruoloInput", ruoloInput);

		return query.getResultList();
	}

	@Override
	public Optional<Utente> findByUsernameAndPassword(String usernameInput, String passwordInput) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Utente> query = entityManager.createQuery(
				"FROM Utente u WHERE u.username = :usernameInput AND u.password = :passwordInput", Utente.class);
		query.setParameter("usernameInput", usernameInput);
		query.setParameter("passwordInput", passwordInput);

		return query.getResultStream().findFirst();
	}

	@Override
	public Optional<Utente> login(String usernameInput, String passwordInput) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Utente> query = entityManager.createQuery(
				"SELECT u FROM Utente u JOIN FETCH u.ruoli r WHERE u.username = :usernameInput AND u.password = :passwordInput and u.stato = :statoUtente",
				Utente.class);
		query.setParameter("usernameInput", usernameInput);
		query.setParameter("passwordInput", passwordInput);
		query.setParameter("statoUtente", StatoUtente.ATTIVO);
		
		return query.getResultStream().findFirst();
	}

}
