package it.prova.pizzastore.servlet.pizzaiolo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

@WebServlet("/ExecuteInsertOrdineServlet")
public class ExecuteInsertOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String codiceOrdineParam = request.getParameter("codice");
		String statoOrdine = request.getParameter("stato");
		String dataOrdineParam = request.getParameter("data");
		String[] idPizzaParam = request.getParameterValues("pizze");
		String clienteOrdineParam = request.getParameter("cliente.id");
		String fattorinoOrdineParam = request.getParameter("utente.id");

		Ordine nuovoOrdine = new Ordine();

		try {
			nuovoOrdine = UtilityForm.initializeOrdineFromParams(codiceOrdineParam, dataOrdineParam, clienteOrdineParam,
					idPizzaParam, fattorinoOrdineParam);
			nuovoOrdine.setClosed(Boolean.parseBoolean(statoOrdine));

			if (!UtilityForm.validateOrdineBean(nuovoOrdine)) {

				request.setAttribute("insert_ordine_attr", nuovoOrdine);
				request.setAttribute("list_utente_attr", MyServiceFactory.getUtenteServiceInstance().listAll());

				request.setAttribute("list_pizza_attr", MyServiceFactory.getPizzaServiceInstance().listAll());
				request.setAttribute("list_cliente_attribute", MyServiceFactory.getClienteServiceInstance().listAll());

				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/pizzaiolo/pizzaioloinsertordine.jsp").forward(request, response);
				return;
			}
			nuovoOrdine.setPrezzoTotaleOrdine(MyServiceFactory.getOrdineServiceInstance()
					.calcolaPrezzoOrdine(nuovoOrdine, nuovoOrdine.getPizze()));
			MyServiceFactory.getOrdineServiceInstance().inserisci(nuovoOrdine);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizzaiolo/pizzaioloinsertordine.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		// uso il sendRedirect con parametro per evitare il problema del double save on
		// refresh
		response.sendRedirect("ExecuteListOrdineServlet?operationResult=SUCCESS");

	}

}
