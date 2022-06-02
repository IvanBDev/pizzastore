package it.prova.pizzastore.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.pizzastore.model.Ruolo;

public class RuoloDAOImpl implements RuoloDAO{
	
	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		// TODO Auto-generated method stub
		this.entityManager = entityManager;
	}

	@Override
	public List<Ruolo> list() throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createQuery("FROM Ruolo", Ruolo.class).getResultList();
	}

	@Override
	public Optional<Ruolo> findOne(Long id) throws Exception {
		// TODO Auto-generated method stub
		Ruolo result = entityManager.find(Ruolo.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Ruolo input) throws Exception {
		// TODO Auto-generated method stub
		if(input == null) 
			throw new Exception("Problema valore in input");
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Ruolo input) throws Exception {
		// TODO Auto-generated method stub
		if(input == null) 
			throw new Exception("Problema valore in input");
		entityManager.persist(input);
	}

	@Override
	public void delete(Ruolo input) throws Exception {
		// TODO Auto-generated method stub
		if(input == null) 
			throw new Exception("Problema valore in input");
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public Ruolo findByDescrizioneAndCodice(String descrizioneInput, String codiceInput) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Ruolo> query = entityManager
				.createQuery("SELECT r FROM Ruolo r WHERE r.descrizione = :descrizioneInput and r.codice = :codiceInput", Ruolo.class);
		query.setParameter("descrizioneInput", descrizioneInput);
		query.setParameter("codiceInput", codiceInput);
		
		
		return query.getResultStream().findFirst().orElse(null);
	}
	
}
