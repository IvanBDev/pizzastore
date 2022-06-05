package it.prova.pizzastore.service;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import it.prova.pizzastore.dao.OrdineDAO;
import it.prova.pizzastore.exception.ElementNotFoundException;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.web.listener.LocalEntityManagerFactoryListener;

public class OrdineServiceImpl implements OrdineService {

	private OrdineDAO ordineDAO;

	@Override
	public void setOrdineDAO(OrdineDAO ordineDAO) {
		// TODO Auto-generated method stub
		this.ordineDAO = ordineDAO;
	}

	@Override
	public List<Ordine> listAll() throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Ordine caricaSingoloElemento(Long id) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.findOne(id).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Ordine ordineInstance) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			ordineDAO.setEntityManager(entityManager);

			ordineDAO.update(ordineInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisci(Ordine ordineInstance) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			ordineDAO.setEntityManager(entityManager);

			ordineDAO.insert(ordineInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Long idOrdine) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			ordineDAO.setEntityManager(entityManager);

			Ordine ordineDelete = ordineDAO.findOne(idOrdine).orElse(null);

			if (ordineDelete == null)
				throw new ElementNotFoundException("Ordine con id " + idOrdine + " non trovato");

			ordineDAO.delete(ordineDelete);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Ordine> trovaTramiteEsempio(Ordine example) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			ordineDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return ordineDAO.findByExample(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiungiPizza(Ordine ordineInstance, Pizza pizzaInstance) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			ordineDAO.setEntityManager(entityManager);

			// 'attacco' alla sessione di hibernate i due oggetti
			// così jpa capisce che se è già presente quel ruolo non deve essere inserito
			ordineInstance = entityManager.merge(ordineInstance);
			pizzaInstance = entityManager.merge(pizzaInstance);

			ordineInstance.getPizze().add(pizzaInstance);
			// l'update non viene richiamato a mano in quanto
			// risulta automatico, infatti il contesto di persistenza
			// rileva che utenteEsistente ora è dirty vale a dire che una sua
			// proprieta ha subito una modifica (vale anche per i Set ovviamente)

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public int calcolaPrezzoOrdine(Ordine ordineInstance, Set<Pizza> prezzoBasePizze) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

				try {
					ordineDAO.setEntityManager(entityManager);
					
					return ordineDAO.getTotalPrice(ordineInstance, prezzoBasePizze);
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				} finally {
					LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
				}
	}

	@Override
	public Ordine caricaSingoloElementoEager(Long idOrdine) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.findOneEager(idOrdine).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Ordine> trovaIlTuoOrdineAssegnato(Long idUtente) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.getYourOrder(idUtente);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}
