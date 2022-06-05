package it.prova.pizzastore.dao;

import java.util.List;
import java.util.Set;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;

public interface OrdineDAO extends IBaseDAO<Ordine>{
	
	public List<Ordine> findByExample (Ordine example) throws Exception;
	
	public Integer getTotalPrice(Ordine ordineInstance, Set<Pizza> prezzoBasePizze) throws Exception;
	
}
