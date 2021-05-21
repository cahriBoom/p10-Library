<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
    	<%@ include file="header.jsp" %>
    </head>
    <body>
		<div id="container">
			<%@ include file="menu.jsp" %>
            <div align="center" class="contenu">
				<h1>Ajouter un Livre</h1>
				<form:form method="post" modelAttribute="livreBean" action="">
				    <div align="center" class="table-mg">
						
						<table class="table table-mg">
							<tr>
								<td>Nom</td>
								<td><form:input path="nom" type="text" class="form-control" required="required"/></td>
							</tr>
							<tr>
								<td>Auteur</td>
								<td><form:input path="auteur" type="text" class="form-control" required="required"/></td>
							</tr>
							<tr>
								<td>Date de Parution</td>
								<td><form:input path="parution" type="text" class="form-control" required="required"/></td>							
							</tr>
							<tr>
								<td>Genre</td>
								<td><form:input path="genre" type="text" class="form-control" required="required"/></td>
							</tr>
							<tr>
								<td>Synopsis</td>
								<td><form:input path="synopsis" type="text" class="form-control" required="required"/></td>
							</tr>
						</table>
						
						<button type="submit" class="btn btn-primary mb-2">Ajouter</button>
				    </div>
				    <div align="center" class="content-button">
						<a href="${contextPath}/accueil"> Retour Accueil</a>
					</div>
				</form:form>
			</div>
		</div>
    </body>
</html>