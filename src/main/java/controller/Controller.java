package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/enviando" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();
	
	
	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		System.out.println(action);

		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/enviando")) {
			novoContato(request, response);
		}else {
			response.sendRedirect("index.html");
			
		}
	}

	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.sendRedirect("agenda.jsp");
		//Criando novo objeto que vai receber  dados do JAVABEANS
		ArrayList<JavaBeans> lista = dao.listarContatos();
		//Encaminahr a lista ao Doc agenda.jsp
		
		request.setAttribute("contatos",lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");		
		rd.forward(request, response);
		
	/*	
	for (int 0 = 1; i < lista.size(); i++){
			System.out.println(lista.get(i).getIdcon());
			System.out.println(lista.get(i).getNome());
			System.out.println(lista.get(i).getPhone());
			System.out.println(lista.get(i).getEmail());
			
	
	}
		*/
		
	}

	// Novo contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//Teste de recebimento dos dados do FORMULARIO
		
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("fone"));
		System.out.println(request.getParameter("email"));
		// setar as variaveis JavaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setPhone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		
		//Invocar o metodo inserir contato passando por objeto contato
		
		dao.inserirContato(contato);
		//Redirecionar para o doc agenda .jsp
		response.sendRedirect("main");
		
		
	}
}
