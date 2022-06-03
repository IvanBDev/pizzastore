<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../admin/header.jsp" />
	   
	   <title>Ricerca</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../admin/navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Modifica elementi</h5> 
				    </div>
				    <div class='card-body'>
		
							<form method="post" action="ExecuteUpdateClienteServlet" class="row g-3" >
								
								<input type = "hidden" name = "idCliente" value = "${update_cliente_attr.id}">
							
								<div class="col-md-6">
									<label for="nome" class="form-label">Nome </label>
									<input type="text" name="nome" id="nome" class="form-control" value = "${update_cliente_attr.nome}">
								</div>
								
								<div class="col-md-6">
									<label for="cognome" class="form-label">Cognome </label>
									<input type="text" name="cognome" id="cognome" class="form-control" value = "${update_cliente_attr.cognome}">
								</div>
							
								<div class="col-md-6">
									<label for="indirizzo" class="form-label">Indirizzo </label>
									<input type="text" class="form-control" name="indirizzo" id="indirizzo" value = "${update_cliente_attr.indirizzo}">
								</div>
								
								<div class="col-md-3">
									<label for="stato" class="form-label">Stato </label>
								    <select class="form-select" id="stato" name="stato" >
								    	<option value="" selected> - Selezionare - </option>
								      	<option value="True" ${update_cliente_attr.attivo == "'true' ? selected ':'"}>ATTIVO</option>
								      	<option value="False" ${update_cliente_attr.attivo == "'false' ? selected ':'"}>NON ATTIVO</option>
								    </select>
								</div>
								
								
							<div class="col-12">
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
								<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
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
			<jsp:include page="../admin/footer.jsp" />
	  </body>
</html>