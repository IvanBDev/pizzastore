<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Modifica</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Modifica l'elemento selezionato</h5> 
				    </div>
				    <div class='card-body'>
							<form method="post" action="ExecuteUpdateOrdineServlet" class="row g-3" novalidate="novalidate">
							
								<div class="col-md-6">
									<label for="codice" class="form-label">Codice:</label>
	                        		<input class="form-control" id="codice" type="text" name="codice" value = "${update_ordine_attr.codice}">
								</div>
								
								<div class="col-md-3">
									<label for="stato" class="form-label">Stato Ordine:</label>
								    <select class="form-select" id="stato" name="stato" required>
								    	<option value="" selected> - Selezionare - </option>
								      	<option value="True" ${update_ordine_attr.closed == 'True'?'selected':''} >CHIUSO</option>
								      	<option value="False" ${update_ordine_attr.closed == 'False'?'selected':''} >NON CHIUSO</option>
								    </select>
								</div>
							
								<div class="col-md-6">
									<label for="data" class="form-label">Data:</label>
									<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${update_ordine_attr.data}' />
	                        		<input class="form-control" id="data" type="date" title="formato : gg/mm/aaaa"  name="data" value = "${parsedDate}">
								</div>
								
								<div>
									<label for = "pizza.id" class = "form-label">Pizze:</label><br>
									<c:forEach items = "${list_pizza_attr}" var = "pizzaItem">
										<input type = "checkbox" id = "pizza.id" name = "pizza.id" value = "${pizzaItem.id}">${pizzaItem.descrizione}<br>
									</c:forEach>
								</div>
								
								<div class="col-md-6">
									<label for="cliente.id">Cliente:</label>
								    <select class="form-select" id="cliente.id" name="cliente.id">
								    	<option value="" selected> -- Selezionare una voce -- </option>
								      	<c:forEach items="${list_cliente_attr}" var="clienteItem">
								      		<option value="${clienteItem.id}" ${update_ordine_attr.cliente.id == clienteItem.id?'selected':''}>${clienteItem.nome} ${clienteItem.cognome}</option>
								      	</c:forEach>
								    </select>
								</div>
							
								<div class="col-md-6">
									<label for="utente.id">Fattorino:<span class="text-danger">*</span></label>
								    <select class="form-select" id="utente.id" name="utente.id">
								    	<option value="" selected> -- Selezionare una voce -- </option>
								      	<c:forEach items="${list_utente_attr}" var="utenteItem">
								      		<option value="${utenteItem.id}" ${update_ordine_attr.utente.id == utenteItem.id?'selected':''}>${utenteItem.nome} ${utenteItem.cognome}</option>
								      	</c:forEach>
								    </select>
								</div>
								
								<input type="hidden" name="idOrdine" value="${update_ordine_attr.id}">
								
							<div class="col-12">
								<button type="submit" name="insertSubmit" value="insertSubmit" id="insertSubmit" class="btn btn-primary">Conferma</button>
							</div>
		
						</form>
  
				    
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>