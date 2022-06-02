package it.prova.pizzastore.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.service.MyServiceFactory;

@WebServlet("/ExecuteSearchClientiServlet")
public class ExecuteSearchClientiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String indirizzoParam = request.getParameter("indirizzo");
		
		Cliente example = new Cliente(nomeParam, cognomeParam, indirizzoParam);
		try {
			request.setAttribute("clienti_list_attr", MyServiceFactory.getClienteServiceInstance().trovaTramiteEsempio(example));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/admin/adminsearch.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("admin/adminlist.jsp").forward(request, response);
		
	}

}
