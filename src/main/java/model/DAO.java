package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

	// Modulo de Conexao
	// Parametros de conexao

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/dbagenda?useTimezone=true&serverTimezone=UTC";

	private String user = "root";
	private String password = "123456";

	// Metodo de conexao

	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	/*
	 * Teste de conexao !
	 * 
	 * public void testeConexao() { try { Connection con = conectar();
	 * System.out.println(con); con.close();
	 * 
	 * } catch (Exception e) { System.out.println(e);
	 */

	/** CRUD CREAT **/
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome, fone, email) values(?, ?, ?)";

		try {
			// abrir a conexao com o banco
			Connection con = conectar();
			// Preparar a query para executar no banco de dados
			PreparedStatement pst = con.prepareStatement(create);

			// Subustituir os parametros (?)
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getPhone());
			pst.setString(3, contato.getEmail());

			// Executar a Query
			pst.executeUpdate();

			// Encerrar a conexao com o banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// CRUD READ VETOR DINAMICO
	public ArrayList<JavaBeans> listarContatos() {
		// Criando um objeto para ser conectado a Classe Java Beans
		ArrayList<JavaBeans> contatos = new ArrayList<>();

		String read = "select * from contatos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// la;o sera executado em quanto houve contatos
			while (rs.next()) {
				// variaveis de apoio que recebem os dados do banco
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String phone = rs.getString(3);
				String email = rs.getString(4);

				// Populando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, phone, email));
			}

			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	/** CRUD UPDATE **/
	// Selecionar o contato

	public void selecionarContato(JavaBeans contato) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contato.getIdcon());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				//Setar as variaveis JavaBEans
				
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setPhone(rs.getString(3));
				contato.setEmail(rs.getString(4));
				
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
		// editar o contato
			public void alterarContato(JavaBeans contato) {
				String create = "update contatos set nome=?, fone=?, email=? where idcon=?";
				try {
					Connection con = conectar();
					PreparedStatement pst = con.prepareStatement(create);
					pst.setString(1, contato.getNome());
					pst.setString(2, contato.getPhone());
					pst.setString(3, contato.getEmail());
					pst.setString(4, contato.getIdcon());
					pst.executeUpdate();
					con.close();
					
					
				} catch (Exception e) {
						System.out.println(e);
			}
				
				
			}
				/** CRUD DELETE **/
			public void deletarContato(JavaBeans contato) {
				String delete = "delete from contatos where idcon = ?";
				try {
					Connection  con = conectar();
					PreparedStatement pst = con.prepareStatement(delete);
					pst.setString(1, contato.getIdcon());
					pst.executeUpdate();
					con.close();
					
				} catch (Exception e) {
					System.out.println(e);
				}
			}	
}
