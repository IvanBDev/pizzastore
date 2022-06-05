<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="../pizzaiolo/header.jsp" />
	 	
	   <title>Visualizza Elemento</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../pizzaiolo/navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Visualizza dettaglio</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${list_ordine_attr.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Closed:</dt>
							  <dd class="col-sm-9">${list_ordine_attr.closed}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Codice:</dt>
							  <dd class="col-sm-9">${list_ordine_attr.codice}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${list_ordine_attr.data}" /></dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Prezzo Totale Ordine:</dt>
							  <dd class="col-sm-9">${list_ordine_attr.prezzoTotaleOrdine}</dd>
					    	</dl>
					    	
					    	<!-- info Cliente e Fattorino-->
					    	<p>
							  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
							    Info Cliente e Fattorino
							  </a>
							</p>
							<div class="collapse" id="collapseExample">
							  <div class="card card-body">
							  	<dl class="row">
								  <dt class="col-sm-3 text-right">Nome:</dt>
								  <dd class="col-sm-9">${list_ordine_attr.cliente.nome}</dd>
							   	</dl>
							   	<dl class="row">
								  <dt class="col-sm-3 text-right">Cognome:</dt>
								  <dd class="col-sm-9">${list_ordine_attr.cliente.cognome}</dd>
							   	</dl>
							   	<dl class="row">
								  <dt class="col-sm-3 text-right">Indirizzo:</dt>
								  <dd class="col-sm-9">${list_ordine_attr.cliente.indirizzo}</dd>
							   	</dl>
							  </div>
							
							<br>
					    
							<div class="collapse" id="collapseExample">
							  <div class="card card-body">
							  	<dl class="row">
								  <dt class="col-sm-3 text-right">Nome:</dt>
								  <dd class="col-sm-9">${list_ordine_attr.utente.nome}</dd>
							   	</dl>
							   	<dl class="row">
								  <dt class="col-sm-3 text-right">Cognome:</dt>
								  <dd class="col-sm-9">${list_ordine_attr.utente.cognome}</dd>
							   	</dl>
							  </div>
							<!-- end info cliente e Fattorino -->
							
							</div>
					    	
					    <!-- end card body -->
					    </div>
					    
					    <div class='card-footer'>
					        <a href="ExecuteListOrdineServlet" class='btn btn-outline-secondary' style='width:80px'>
					            <i class='fa fa-chevron-left'></i> Back
					        </a>
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