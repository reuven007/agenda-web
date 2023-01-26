<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans" %>
<%@ page import="java.util.ArrayList" %>

<% 
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>)
request.getAttribute("contatos");

%>


<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de Contatos</title>

<link rel="icon" href="imagens/phone01.png">
<link rel="stylesheet" href="Style.css">

</head>
<body>
	
	<h1>Agenda de Contatos</h1>
	<a href="novo.html" class="Botao1" >Novo contato</a>
	
	<table>
		<thead>
			<tr>
				<th> Id </th>
				<th> Nome </th>
				<th> Phone </th>
				<th> E-mail </th>
			
			</tr>
		<tbody>
			<%for (int i = 0; i < lista.size(); i++){ %>
				<tr> 
					<td> <%=lista.get(i).getIdcon() %></td>
						<td> <%=lista.get(i).getNome() %></td>
							<td> <%=lista.get(i).getPhone() %></td>
								<td> <%=lista.get(i).getEmail() %></td>
					
				</tr>
			
			
			<%} %>
		
		</tbody>
	
	</table>
	
	
</body>
</html>