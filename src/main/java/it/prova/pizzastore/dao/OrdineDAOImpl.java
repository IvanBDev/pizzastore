package it.prova.pizzastore.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;

public class OrdineDAOImpl implements OrdineDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		// TODO Auto-generated method stub
		this.entityManager = entityManager;
	}

	@Override
	public List<Ordine> list() throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createQuery("FROM Ordine", Ordine.class).getResultList();
	}

	@Override
	public Optional<Ordine> findOne(Long id) throws Exception {
		// TODO Auto-generated method stub
		Ordine result = entityManager.find(Ordine.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Ordine input) throws Exception {
		// TODO Auto-generated method stub
		if (input == null)
			throw new Exception("Problema valore in input");
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Ordine input) throws Exception {
		// TODO Auto-generated method stub
		if (input == null)
			throw new Exception("Problema valore in input");
		entityManager.merge(input);
	}

	@Override
	public void delete(Ordine input) throws Exception {
		// TODO Auto-generated method stub
		if (input == null)
			throw new Exception("Problema valore in input");
		input.setClosed(true);
	}

	@Override
	public List<Ordine> findByExample(Ordine example) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("SELECT o FROM Ordine o WHERE o.id = o.id");

		if (StringUtils.isNotEmpty(example.getCodice())) {
			whereClauses.add(" o.codice  like :codice ");
			paramaterMap.put("codice", "%" + example.getCodice() + "%");
		}

		if (example.getData() != null) {
			whereClauses.add(" o.data  >= :data ");
			paramaterMap.put("data", example.getData());
		}
		
		if(example.isClosed() == true) {
			whereClauses.add("o.closed = :true");
			paramaterMap.put("true", true);
		}
		else {
			whereClauses.add("o.closed = :false");
			paramaterMap.put("false", false);
		}

		if (example.getUtente() != null) {
			whereClauses.add(" o.utente_id  = :utente_id ");
			paramaterMap.put("utente_id", example.getUtente());
		}

		if (example.getCliente() != null) {
			whereClauses.add(" o.cliente_id  = :cliente_id ");
			paramaterMap.put("cliente_id", example.getCliente());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Ordine> typedQuery = entityManager.createQuery(queryBuilder.toString(), Ordine.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	public Integer getTotalPrice(Ordine ordineInstance, Set<Pizza> prezzoBasePizze) throws Exception {
		// TODO Auto-generated method stub
		Integer sommaTotale = 0;
		for (Pizza pizzaItem : prezzoBasePizze) {
			sommaTotale += pizzaItem.getPrezzoBase();
		}
		
		ordineInstance.setPrezzoTotaleOrdine(sommaTotale);
		
		return ordineInstance.getPrezzoTotaleOrdine();
		
	}

	@Override
	public Optional<Ordine> findOneEager(Long idOrdineInstance) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Ordine> query = entityManager.createQuery("SELECT o FROM Ordine o LEFT JOIN FETCH o.cliente c LEFT JOIN FETCH o.utente u WHERE o.id = :idOrdine", Ordine.class);
		query.setParameter("idOrdine", idOrdineInstance);
		
		return query.getResultList().stream().findFirst();
	}

	@Override
	public List<Ordine> getYourOrder(Long idUtente) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Ordine> query = entityManager.createQuery("SELECT o FROM Ordine o LEFT JOIN FETCH o.utente u WHERE u.id = :idUtente AND o.closed = false", Ordine.class);
		query.setParameter("idUtente", idUtente);
		
		return query.getResultList();
	}

}
