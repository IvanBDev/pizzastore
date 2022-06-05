package it.prova.pizzastore.servlet.fattorino;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.exception.ElementNotFoundException;
import it.prova.pizzastore.service.MyServiceFactory;

@WebServlet("/ExecuteDeleteMioOrdineServlet")
public class ExecuteDeleteMioOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idMioOrdineParam = request.getParameter("idOrdine");

		if (!NumberUtils.isCreatable(idMioOrdineParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/fattorino/fattorinohomepage").forward(request, response);
			return;
		}

		try {

			MyServiceFactory.getOrdineServiceInstance().rimuovi(Long.parseLong(idMioOrdineParam));

		} catch (ElementNotFoundException e) {

			request.getRequestDispatcher("fattorino/fatorinolist.jsp").forward(request, response);
			return;

		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/fattorino/fattorinohomepage").forward(request, response);
			return;

		}

		response.sendRedirect("PrepareFattorinoListServlet?idFattorino=${userInfo.id}");
	}

}
