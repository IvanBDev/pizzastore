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

@WebServlet("/ExecuteUpdateOrdineServlet")
public class ExecuteUpdateOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idOrdineParam = request.getParameter("idOrdine");
		String codiceOrdineParam = request.getParameter("codice");
		String statoOrdine = request.getParameter("stato");
		String dataOrdineParam = request.getParameter("data");
		String[] idPizzaParam = request.getParameterValues("pizza.id");
		String clienteOrdineParam = request.getParameter("cliente.id");
		String fattorinoOrdineParam = request.getParameter("utente.id");

		Ordine ordineUpdate = new Ordine();

		try {
			ordineUpdate = UtilityForm.initializeOrdineFromParams(codiceOrdineParam, dataOrdineParam,
					clienteOrdineParam, idPizzaParam, fattorinoOrdineParam);
			ordineUpdate.setClosed(Boolean.parseBoolean(statoOrdine));
			ordineUpdate.setId(Long.parseLong(idOrdineParam));

			if (!UtilityForm.validateOrdineBean(ordineUpdate)) {
				request.setAttribute("update_ordine_attr", ordineUpdate);
				request.setAttribute("list_utente_attr", MyServiceFactory.getUtenteServiceInstance().listAll());

				request.setAttribute("list_pizza_attr", MyServiceFactory.getPizzaServiceInstance().listAll());
				request.setAttribute("list_cliente_attr", MyServiceFactory.getClienteServiceInstance().listAll());

				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/pizzaiolo/pizzaioloupdateordine.jsp").forward(request, response);
				return;
			}

			ordineUpdate.setPrezzoTotaleOrdine(MyServiceFactory.getOrdineServiceInstance()
					.calcolaPrezzoOrdine(ordineUpdate, ordineUpdate.getPizze()));
			MyServiceFactory.getOrdineServiceInstance().aggiorna(ordineUpdate);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizzaiolo/pizzaioloupdateordine.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect("ExecuteListOrdineServlet?operationResult=SUCCESS");
		
	}

}
