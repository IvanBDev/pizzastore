package it.prova.pizzastore.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.pizzastore.dao.ClienteDAO;
import it.prova.pizzastore.exception.ElementNotFoundException;
import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.web.listener.LocalEntityManagerFactoryListener;

public class ClienteServiceImpl implements ClienteService {

	private ClienteDAO clienteDAO;

	@Override
	public void setClienteDAO(ClienteDAO clienteDAO) {
		// TODO Auto-generated method stub
		this.clienteDAO = clienteDAO;
	}

	@Override
	public List<Cliente> listAll() throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			clienteDAO.setEntityManager(entityManager);

			return clienteDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Cliente caricaSingoloElemento(Long id) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			clienteDAO.setEntityManager(entityManager);
			return clienteDAO.findOne(id).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Cliente clienteInstance) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			clienteDAO.setEntityManager(entityManager);

			clienteDAO.update(clienteInstance);

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
	public void inserisci(Cliente clienteInstance) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			clienteDAO.setEntityManager(entityManager);

			clienteDAO.insert(clienteInstance);

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
	public void rimuovi(Long idCliente) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			clienteDAO.setEntityManager(entityManager);

			Cliente clienteDelete = clienteDAO.findOne(idCliente).orElse(null);
			if (clienteDelete == null)
				throw new ElementNotFoundException("Cliente con id " + idCliente + " non trovato");

			clienteDAO.delete(clienteDelete);

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
	public List<Cliente> trovaTramiteEsempio(Cliente example) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			clienteDAO.setEntityManager(entityManager);
			
			return clienteDAO.findByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
}
