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

@WebServlet("/PrepareDeleteMioOrdineServlet")
public class PrepareDeleteMioOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idMioOrdineParam = request.getParameter("idOrdine");

		if (!NumberUtils.isCreatable(idMioOrdineParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("fattorino/listOrdine.jsp").forward(request, response);
			return;
		}

		try {
			Ordine mioOrdineInstance = MyServiceFactory.getOrdineServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idMioOrdineParam));

			request.setAttribute("delete_ordine_attr", mioOrdineInstance);

			if (mioOrdineInstance == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("PrepareFattorinoListServlet?idFattorino=${userInfo.id}").forward(request, response);
				return;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("fattorino/fatorinolist.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("fattorino/fattorinodelete.jsp").forward(request, response);
	}
}
