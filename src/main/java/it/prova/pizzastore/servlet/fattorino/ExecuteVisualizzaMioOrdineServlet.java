package it.prova.pizzastore.servlet.fattorino;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.MyServiceFactory;

@WebServlet("/ExecuteVisualizzaMioOrdineServlet")
public class ExecuteVisualizzaMioOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idMioOrdineParam = request.getParameter("idOrdine");

		if (!NumberUtils.isCreatable(idMioOrdineParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("pizzaiolohomepage").forward(request, response);
			return;
		}

		try {
			Ordine mioOrdineInstance = MyServiceFactory.getOrdineServiceInstance()
					.caricaSingoloElementoEager(Long.parseLong(idMioOrdineParam));

			if (idMioOrdineParam == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("fattorino/fatorinolist.jsp").forward(request,
						response);
				return;
			}
			
			request.setAttribute("list_mioOrdine_attr", mioOrdineInstance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("homefattorino").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/fattorino/fattorinoshow.jsp").forward(request, response);
	}
}
