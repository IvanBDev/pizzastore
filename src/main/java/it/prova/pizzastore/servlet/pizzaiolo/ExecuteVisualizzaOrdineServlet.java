package it.prova.pizzastore.servlet.pizzaiolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.MyServiceFactory;

@WebServlet("/ExecuteVisualizzaOrdineServlet")
public class ExecuteVisualizzaOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idOrdineParam = request.getParameter("idOrdine");

		if (!NumberUtils.isCreatable(idOrdineParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("pizzaiolohomepage").forward(request, response);
			return;
		}

		try {
			Ordine ordineInstance = MyServiceFactory.getOrdineServiceInstance()
					.caricaSingoloElementoEager(Long.parseLong(idOrdineParam));

			if (idOrdineParam == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListOrdineServlet?operationResult=NOT_FOUND").forward(request,
						response);
				return;
			}
			
			request.setAttribute("list_ordine_attr", ordineInstance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("homepizzaiolo").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/pizzaiolo/pizzaioloshowordine.jsp").forward(request, response);
		
	}
}
