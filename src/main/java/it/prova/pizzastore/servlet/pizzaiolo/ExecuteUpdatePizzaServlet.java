package it.prova.pizzastore.servlet.pizzaiolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

@WebServlet("/ExecuteUpdatePizzaServlet")
public class ExecuteUpdatePizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idPizzaParam = request.getParameter("idPizza");

		if (!NumberUtils.isCreatable(idPizzaParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("pizzaiolo/pizzaiololist.jsp").forward(request, response);
			return;
		}

		String descrizioneParam = request.getParameter("descrizione");
		String ingredientiParam = request.getParameter("ingredienti");
		String prezzoBaseParam = request.getParameter("prezzoBase");
		String attivoParam = request.getParameter("disponibile");

		Pizza pizzaInstance = UtilityForm.createPizzaFromParas(descrizioneParam, ingredientiParam, prezzoBaseParam,
				attivoParam);

		pizzaInstance.setId(Long.parseLong(idPizzaParam));

		if (!UtilityForm.validatePizzaBean(pizzaInstance)) {
			request.setAttribute("update_pizza_attr", pizzaInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/pizzaiolo/pizzaioloupdate.jsp").forward(request, response);
			return;
		}

		try {
			MyServiceFactory.getPizzaServiceInstance().aggiorna(pizzaInstance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizzaiolo/pizzaioloedit.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListPizzeServlet?operationResult=SUCCESS");
	}

}
