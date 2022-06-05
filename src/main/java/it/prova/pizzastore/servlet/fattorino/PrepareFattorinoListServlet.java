package it.prova.pizzastore.servlet.fattorino;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.service.MyServiceFactory;

@WebServlet("/PrepareFattorinoListServlet")
public class PrepareFattorinoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idFattorinoParam = request.getParameter("idFattorino");

		try {
			request.setAttribute("list_ordinifattorino_attr", MyServiceFactory.getOrdineServiceInstance()
					.trovaIlTuoOrdineAssegnato(Long.parseLong(idFattorinoParam)));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/fattorino/fatorinolist.jsp").forward(request, response);
	}
}
