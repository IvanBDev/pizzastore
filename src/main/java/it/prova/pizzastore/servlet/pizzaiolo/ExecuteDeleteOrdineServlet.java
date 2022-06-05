package it.prova.pizzastore.servlet.pizzaiolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.exception.ElementNotFoundException;
import it.prova.pizzastore.service.MyServiceFactory;

@WebServlet("/ExecuteDeleteOrdineServlet")
public class ExecuteDeleteOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idOrdineParam = request.getParameter("idOrdine");

		if (!NumberUtils.isCreatable(idOrdineParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizzaiolo/pizzaiolohomepage").forward(request, response);
			return;
		}

		try {

			MyServiceFactory.getOrdineServiceInstance().rimuovi(Long.parseLong(idOrdineParam));

		} catch (ElementNotFoundException e) {
			
			request.getRequestDispatcher("ExecuteListOrdineServlet?operationResult=NOT_FOUND").forward(request, response);
			return;
			
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizzaiolo/pizzaiolohomepage").forward(request, response);
			return;
			
		}

		response.sendRedirect("ExecuteListOrdineServlet?operationResult=SUCCESS");

	}

}
