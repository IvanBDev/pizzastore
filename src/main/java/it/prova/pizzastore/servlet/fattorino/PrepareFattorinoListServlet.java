package it.prova.pizzastore.servlet.fattorino;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.MyServiceFactory;

@WebServlet("/PrepareFattorinoListServlet")
public class PrepareFattorinoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Utente utenteInSessione = (Utente) request.getSession().getAttribute("userInfo");

		try {
			request.setAttribute("list_ordinifattorino_attr", MyServiceFactory.getOrdineServiceInstance()
					.trovaIlTuoOrdineAssegnato(utenteInSessione.getId()));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/fattorino/fatorinolist.jsp").forward(request, response);
	}
}
