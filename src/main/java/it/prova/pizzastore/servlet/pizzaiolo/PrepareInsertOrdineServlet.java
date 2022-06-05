package it.prova.pizzastore.servlet.pizzaiolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.service.MyServiceFactory;

@WebServlet("/PrepareInsertOrdineServlet")
public class PrepareInsertOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// metto un bean 'vuoto' in request perché per la pagina risulta necessario
		request.setAttribute("insert_ordine_attr", new Cliente());

		try {
			request.setAttribute("list_pizza_attr", MyServiceFactory.getPizzaServiceInstance().listAll());
			request.setAttribute("list_cliente_attr", MyServiceFactory.getClienteServiceInstance().listAll());
			request.setAttribute("list_utente_attr",
					MyServiceFactory.getUtenteServiceInstance().caricaTramiteRuolo(Ruolo.FATTORINO_ROLE));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/pizzaiolo/pizzaioloinsertordine.jsp").forward(request, response);
	}
}
