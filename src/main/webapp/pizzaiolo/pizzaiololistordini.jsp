<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../pizzaiolo/header.jsp" />
	   
	   <title>Pagina dei Risultati</title>
	 </head>
	 
	<body class="d-flex flex-column h-100">
	 
		<!-- Fixed navbar -->
		<jsp:include page="/pizzaiolo/navbar.jsp"></jsp:include>
	 
	
		<!-- Begin page content -->
		<main class="flex-shrink-0">
		  <div class="container">
		  
		  		<div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
				  ${successMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
				<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
				  ${errorMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
		  
		  
		  
		  		<div class='card'>
				    <div class='card-header'>
				        <h5>Lista degli ordini</h5> 
				    </div>
				    <div class='card-body'>
				    	<a class="btn btn-primary " href="PrepareInsertOrdineServlet">Add New</a>
				    
				        <div class='table-responsive'>
				            <table class='table table-striped ' >
				                <thead>
				                    <tr>
			                         	<th>Data</th>
				                        <th>Closed</th>
				                        <th>Codice</th>
				                        <th>Costo Totale Ordine</th>
				                        <th>Azioni</th>
				                    </tr>
				                </thead>
				                <tbody>
				                	<c:forEach items="${list_ordine_attr}" var="ordineItem">
										<tr>
											<td><fmt:formatDate type = "date" value = "${ordineItem.data }" /></td>
											<td>${ordineItem.closed }</td>
											<td>${ordineItem.codice }</td>
											<td>${ordineItem.prezzoTotaleOrdine }</td>
											<td>
												<a class="btn  btn-sm btn-outline-secondary" href="ExecuteVisualizzaOrdineServlet?idOrdine=${ordineItem.id }">Visualizza</a>
												<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="PrepareUpdateOrdineServlet?idOrdine=${ordineItem.id }">Edit</a>
												<a class="btn btn-outline-danger btn-sm" href="PrepareDeleteOrdineServlet?idOrdine=${ordineItem.id }">Delete</a>
											</td>
										</tr>
									</c:forEach>
				                </tbody>
				            </table>
				        </div>
				   
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