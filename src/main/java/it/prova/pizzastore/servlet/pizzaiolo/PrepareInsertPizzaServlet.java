package it.prova.pizzastore.servlet.pizzaiolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Pizza;

@WebServlet("/PrepareInsertPizzaServlet")
public class PrepareInsertPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// metto un bean 'vuoto' in request perché per la pagina risulta necessario
		request.setAttribute("insert_pizza_attr", new Pizza());
		request.getRequestDispatcher("/pizzaiolo/pizzaioloinsert.jsp").forward(request, response);
	}

}
