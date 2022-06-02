package it.prova.pizzastore.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Cliente;


@WebServlet("/PrepareInsertClientiServlet")
public class PrepareInsertClientiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// metto un bean 'vuoto' in request perché per la pagina risulta necessario
		request.setAttribute("insert_cliente_attr", new Cliente());
		request.getRequestDispatcher("/admin/admininsert.jsp").forward(request, response);
	}

}
