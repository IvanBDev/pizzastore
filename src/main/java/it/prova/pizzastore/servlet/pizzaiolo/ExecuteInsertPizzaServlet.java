package it.prova.pizzastore.servlet.pizzaiolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

@WebServlet("/ExecuteInsertPizzaServlet")
public class ExecuteInsertPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String descrizionePizzaParam = request.getParameter("descrizione");
		String ingredientiPizzaParam = request.getParameter("ingredienti");
		String prezzoBasePizzaParam = request.getParameter("prezzoBase");
		String disponibilePizzaParam = request.getParameter("disponibile");

		Pizza nuovaPizza = UtilityForm.createPizzaFromParas(descrizionePizzaParam, ingredientiPizzaParam,
				prezzoBasePizzaParam, disponibilePizzaParam);

		// se la validazione non risulta ok
		if (!UtilityForm.validatePizzaBean(nuovaPizza)) {
			request.setAttribute("insert_pizza_attr", nuovaPizza);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/pizzaiolo/pizzaioloinsert.jsp").forward(request, response);
			return;
		}

		// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
		// occupiamoci delle operazioni di business
		try {
			MyServiceFactory.getPizzaServiceInstance().inserisci(nuovaPizza);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizzaiolo/pizzaioloinsert.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		// uso il sendRedirect con parametro per evitare il problema del double save on
		// refresh
		response.sendRedirect("ExecuteListPizzeServlet?operationResult=SUCCESS");

	}

}
