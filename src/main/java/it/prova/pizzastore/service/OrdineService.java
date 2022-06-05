package it.prova.pizzastore.service;

import java.util.List;
import java.util.Set;

import it.prova.pizzastore.dao.OrdineDAO;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;

public interface OrdineService {
	
public void setOrdineDAO(OrdineDAO ordineDAO);
	
	public List<Ordine> listAll() throws Exception;
	
	public Ordine caricaSingoloElemento(Long id) throws Exception;
	
	public void aggiorna(Ordine ordineInstance) throws Exception;
	
	public void inserisci(Ordine ordineInstance) throws Exception;
	
	public void rimuovi(Long idOrdine) throws Exception;
	
	public List<Ordine> trovaTramiteEsempio (Ordine example) throws Exception;
	
	public void aggiungiPizza(Ordine ordineInstance, Pizza pizzaInstance) throws Exception;
	
	public int calcolaPrezzoOrdine(Ordine ordineInstance, Set<Pizza> prezzoBasePizze) throws Exception;
	
	public Ordine caricaSingoloElementoEager(Long idOrdine) throws Exception;
	
	public List<Ordine> trovaIlTuoOrdineAssegnato(Long idUtente) throws Exception;
	
}
