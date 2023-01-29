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

@WebServlet(urlPatterns = { "/Controller", "/main", "/enviando", "/select", "/update", "/delete" })
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
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else if (action.equals("/update")) {
			editarContato(request, response);
		} else if (action.equals("/delete")) {
				removerContato(request, response);
		} else {
			response.sendRedirect("index.html");

		}
	}

	
	

	/*
	 * teste de recebimento System.out.println(contato.getIdcon());
	 * System.out.println(contato.getNome());
	 * System.out.println(contato.getPhone());
	 * System.out.println(contato.getEmail());
	 * 
	 */

	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("agenda.jsp");
		// Criando novo objeto que vai receber dados do JAVABEANS
		ArrayList<JavaBeans> lista = dao.listarContatos();
		// Encaminhar a lista ao Doc agenda.jsp

		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);

		/*
		 * for (int 0 = 1; i < lista.size(); i++){
		 * System.out.println(lista.get(i).getIdcon());
		 * System.out.println(lista.get(i).getNome());
		 * System.out.println(lista.get(i).getPhone());
		 * System.out.println(lista.get(i).getEmail());
		 * 
		 * 
		 * }
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

		// Invocar o metodo inserir contato passando por objeto contato

		dao.inserirContato(contato);
		// Redirecionar para o doc agenda .jsp
		response.sendRedirect("main");
		}

		
		// Editar contato
		private void listarContato(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			// Recebimento do id do contato que sera EDITADO
			String idcon = request.getParameter("idcon");
			
			// Setar a var JavaBEans
			contato.setIdcon(idcon);
			
			// Executar o Metodo selecionarContato (DAO)
			dao.selecionarContato(contato);
			
			// Setar o atributo do formulario
			request.setAttribute("idcon", contato.getIdcon());
			request.setAttribute("nome", contato.getNome());
			request.setAttribute("phone", contato.getPhone());
			request.setAttribute("email", contato.getEmail());
			RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
			rd.forward(request, response);
		}
		
		private void editarContato(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
					//Setar as variaveis JavaBeans
						contato.setIdcon(request.getParameter("idcon"));
						contato.setNome(request.getParameter("nome"));
						contato.setPhone(request.getParameter("fone"));
						contato.setEmail(request.getParameter("email"));
					//executar metodo alterar contato
						dao.alterarContato(contato);
					// redirecionar para o Doc agenda.jsp (atualizando alteracoes)
						response.sendRedirect("main");
		
	}       //Remover um contato
		private void removerContato(HttpServletRequest request, HttpServletResponse response) throws IOException {
			String idcon = request.getParameter("idcon");
			
			//Setar variavel idconn JavaBeans
			contato.setIdcon(idcon);
			
			// Executar o metodo deletarContato
			dao.deletarContato(contato);
			
			// Redirecionar para o doc agenda .jsp
			response.sendRedirect("main");
			}
    }


