<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Ricerca</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
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
		
							<form method="post" action="ExecuteUpdatePizzaServlet" class="row g-3" >
								
								<input type = "hidden" name = "idPizza" value = "${update_pizza_attr.id}">
							
								<div class="col-md-6">
									<label for="descrizione" class="form-label">Descrizione </label>
									<input type="text" name="descrizione" id="descrizione" class="form-control" value = "${update_pizza_attr.descrizione}">
								</div>
								
								<div class="col-md-6">
									<label for="ingredienti" class="form-label">Ingredienti </label>
									<input type="text" name="ingredienti" id="ingredienti" class="form-control" value = "${update_pizza_attr.ingredienti}">
								</div>
							
								<div class="col-md-6">
									<label for="prezzoBase" class="form-label">Prezzo Base </label>
									<input type="text" class="form-control" name="prezzoBase" id="prezzoBase" value = "${update_pizza_attr.prezzoBase}">
								</div>
								
								<div class="col-md-3">
									<label for="disponibile" class="form-label">Disponibile </label>
								    <select class="form-select" id="disponibile" name="disponibile" >
								    	<option value="" selected> - Selezionare - </option>
								      	<option value="True" ${update_pizza_attr.attivo == "'true' ? selected ':'"}>DISPONIBILR</option>
								      	<option value="False" ${update_pizza_attr.attivo == "'false' ? selected ':'"}>NON DISPONIBILE</option>
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
			<jsp:include page="../footer.jsp" />
	  </body>
</html>