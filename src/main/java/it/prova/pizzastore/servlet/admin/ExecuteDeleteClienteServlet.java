package it.prova.pizzastore.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.exception.ElementNotFoundException;
import it.prova.pizzastore.service.MyServiceFactory;

@WebServlet("/ExecuteDeleteClienteServlet")
public class ExecuteDeleteClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idClienteParam = request.getParameter("idCliente");

		if (!NumberUtils.isCreatable(idClienteParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("admin/adminhomepage.jsp").forward(request, response);
			return;
		}

		try {
			// novità rispetto al passato: abbiamo un overload di rimuovi che agisce per id
			// in questo modo spostiamo la logica di caricamento/rimozione nel service
			// usando la stessa finestra di transazione e non aprendo e chiudendo due volte
			// inoltre mi torna utile quando devo fare rimozioni eager
			MyServiceFactory.getClienteServiceInstance().rimuovi(Long.parseLong(idClienteParam));
		} catch (ElementNotFoundException e) {
			request.getRequestDispatcher("ExecuteListClientiServlet?operationResult=NOT_FOUND").forward(request,
					response);
			return;
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("admin/adminhomepage.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListClientiServlet?operationResult=SUCCESS");
	}
}
