<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../pizzaiolo/header.jsp" />
	   
	   <title>Inserisci Nuovo Elemento</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../pizzaiolo/navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
					  Esempio di operazione fallita!
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
					  Aggiungere d-none nelle class per non far apparire
					   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Inserisci nuovo elemento</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form method="post" action="ExecuteInsertOrdineServlet" class="row g-3" novalidate="novalidate">
							
								<div class="col-md-6">
									<label for="codice" class="form-label">Codice:<span class="text-danger">*</span></label>
	                        		<input class="form-control" id="codice" type="text" placeholder="Inserisci il codice" name="codice">
								</div>
								
								<div class="col-md-3">
									<label for="stato" class="form-label">Stato: <span class="text-danger">*</span></label>
								    <select class="form-select" id="stato" name="stato" required>
								    	<option value="" selected> - Selezionare - </option>
								      	<option value="True" ${insert_pizza_attr.attivo == 'True'?'selected':''} >ATTIVO</option>
								      	<option value="False" ${insert_pizza_attr.attivo == 'False'?'selected':''} >NON ATTIVO</option>
								    </select>
								</div>
							
								<div class="col-md-6">
									<label for="data" class="form-label">Data:<span class="text-danger">*</span></label>
	                        		<input class="form-control" id="data" type="date" placeholder="dd/MM/yy" title="formato : gg/mm/aaaa"  name="data">
								</div>
								
								<div>
									<label for = "pizze" class = "form-label">Pizze:<span class="text-danger">*</span></label><br>
									<c:forEach items = "${list_pizza_attr}" var = "pizzaItem">
										<input type = "checkbox" id = "pizze" name = "pizze" value = "${pizzaItem.id}">${pizzaItem.descrizione}<br>
									</c:forEach>
								</div>
								
								<div class="col-md-6">
									<label for="cliente.id">Cliente:<span class="text-danger">*</span></label>
								    <select class="form-select" id="cliente.id" name="cliente.id">
								    	<option value="" selected> -- Selezionare una voce -- </option>
								      	<c:forEach items="${list_cliente_attr}" var="clienteItem">
								      		<option value="${clienteItem.id}">${clienteItem.nome} ${clienteItem.cognome}</option>
								      	</c:forEach>
								    </select>
								</div>
							
								<div class="col-md-6">
									<label for="cliente.id">Fattorino:<span class="text-danger">*</span></label>
								    <select class="form-select" id="utente.id" name="utente.id">
								    	<option value="" selected> -- Selezionare una voce -- </option>
								      	<c:forEach items="${list_utente_attr}" var="utenteItem">
								      		<option value="${utenteItem.id}">${utenteItem.nome} ${utenteItem.cognome}</option>
								      	</c:forEach>
								    </select>
								</div>
								
								
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
			<jsp:include page="../pizzaiolo/footer.jsp" />
	  </body>
</html>