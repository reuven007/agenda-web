package model;

public class JavaBeans {

	private String idcon;
	private String nome;
	private String phone;
	private String email;
	public String getIdcon() {
		return idcon;
	}
	public void setIdcon(String idcon) {
		this.idcon = idcon;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public JavaBeans() {
		super();
	
		
	}
	public JavaBeans(String idcon, String nome, String phone, String email) {
		super();
		this.idcon = idcon;
		this.nome = nome;
		this.phone = phone;
		this.email = email;
	}
	
}
