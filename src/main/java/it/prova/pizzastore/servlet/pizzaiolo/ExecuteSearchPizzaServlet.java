package it.prova.pizzastore.servlet.pizzaiolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;


@WebServlet("/ExecuteSearchPizzaServlet")
public class ExecuteSearchPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String descrizionePizzaParam = request.getParameter("descrizione");
		String ingredientiPizzaParam = request.getParameter("ingredienti");
		String prezzoBasePizzaParam = request.getParameter("prezzoBase");
		
		Pizza example = new Pizza(descrizionePizzaParam, ingredientiPizzaParam);
		example.setPrezzoBase(Integer.parseInt(prezzoBasePizzaParam));
		
		try {
			request.setAttribute("pizza_list_attr", MyServiceFactory.getPizzaServiceInstance().trovaTramiteEsempio(example));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizzaiolo/pizzaiolosearch.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("pizzaiolo/pizzaiololist.jsp").forward(request, response);
		
		
	}

}
