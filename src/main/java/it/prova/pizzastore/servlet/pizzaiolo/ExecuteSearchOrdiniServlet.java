package it.prova.pizzastore.servlet.pizzaiolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

@WebServlet("/ExecuteSearchOrdiniServlet")
public class ExecuteSearchOrdiniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String codiceOrdineParam = request.getParameter("codice");
		String dataOrdineParam = request.getParameter("data");	
		String statoOrdineParam = request.getParameter("stato");
		
		Ordine example = UtilityForm.createOrdineFromParas(codiceOrdineParam, dataOrdineParam, statoOrdineParam);
		try {
			request.setAttribute("list_ordine_attr", MyServiceFactory.getOrdineServiceInstance()
					.trovaTramiteEsempio(example));	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizzaiolo/pizzaiolosearchordini.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("pizzaiolo/pizzaiololistordini.jsp").forward(request, response);	
		
	}
	
}