/**
 * Confirmacao de exclusao de um contato
 *	 Reuven Martins
 *
 */
 
 function confirmar (idcon){
	 let resposta = confirm("Confirmar a exxclusao desse contato?");
	 if (resposta === true){
		 //alert(idcon)
		 window.location.href = "delete?idcon=" + idcon;
		 
		 }
		 
	 } 
	
	
	
 