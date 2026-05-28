<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<%@include file="base-head.jsp"%>
	</head>
	<body>
		<%@include file="nav-menu.jsp"%>
			
		<div id="container" class="container-fluid">
			<h3 class="page-header">${action eq "insert" ? "Adicionar Empresa" : "Editar Empresa" }</h3>

			<form action="${pageContext.request.contextPath}/company/${action}" 
			      method="POST">
				<input type="hidden" value="${company_to_edit.getId()}" name="company-id">
				<div class="row">
					<div class="form-group col-md-6">
						<label for="company-name">Nome</label>
						<input type="text" class="form-control" 
						       id="company-name" name="company-name" autofocus="autofocus" 
						       placeholder="Nome da empresa" 
							   required 
							   oninvalid="this.setCustomValidity('Por favor, informe o nome da empresa.')"
							   oninput="setCustomValidity('')"
							   value="${company_to_edit.getName()}">
					</div>
					
					<div class="form-group col-md-6">
						<label for="user-role">Cargo</label>
						<input type="text" class="form-control" 
						       id="user-role" name="user-role" autofocus="autofocus" 
						       placeholder="Cargo do usuário" 
							   required 
							   oninvalid="this.setCustomValidity('Por favor, informe o cargo do usuário.')"
							   oninput="setCustomValidity('')"
							   value="${company_to_edit.getRole()}">
					</div>
					
					<div class="form-group col-md-6">
						<label for="user-start">Data início</label>
						<input type="date" class="form-control" 
						       id="user-start" name="user-start" autofocus="autofocus" 
						       placeholder="Data de início do usuário" 
							   required 
							   oninvalid="this.setCustomValidity('Por favor, informe a data de início.')"
							   oninput="setCustomValidity('')"
							   value="${company_to_edit.getStart()}">
					</div>
					
					<div class="form-group col-md-6">
						<label for="user-end">Data saída</label>
						<input type="date" class="form-control" 
						       id="user-end" name="user-end" autofocus="autofocus" 
						       placeholder="Data de saída do usuário"
							   value="${company_to_edit.getEnd()}">
					</div>

					<div class="form-group col-md-6">
						<label for="responsible">Responsável</label>
						<select id="responsible" name="responsible" 
						        class="form-control selectpicker"
							    required 
							    oninvalid="this.setCustomValidity('Por favor, informe o responsável.')"
							    oninput="setCustomValidity('')">
						  <option value="" disabled ${not empty company_to_edit ? "" : "selected"}>
						    Selecione um reponsável
						  </option>
						  
						  <c:forEach var="responsible" items="${users}">
						  	<option value="${responsible.getId()}"  
						  	        ${company_to_edit.getUser().getId() == responsible.getId() 
						  	        ? "selected" : ""}>
						  		
						  		${responsible.getName()}
						  	</option>	
						  </c:forEach>
						</select>
					</div>
				</div>
				<hr />
				<div id="actions" class="row pull-right">
					<div class="col-md-12">
						<a href="${pageContext.request.contextPath}/companies" 
						   class="btn btn-default">
							Cancelar
						</a>
						
						<button type="submit" 
						        class="btn btn-primary">
						        
						        ${not empty company_to_edit ? "Alterar Empresa" : "Criar Empresa"}
						</button>
					</div>
				</div>
			</form>
		</div>

		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>
