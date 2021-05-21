<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			<div id="contenu">
				<form:form method="post" modelAttribute="livreBean" action="${contextPath}/search">
					<td>
						<form:input type="text" path="nom" />
						<button type="submit">Search</button>
					</td>
				</form:form>
				<h1>Résultat de Recherche</h1>
				<div align="center">
					<table class="table table-mg">
					<tr>
						<td scope="col">Nom</td>
						<td scope="col">Date de parution</td>
						<td scope="col">Auteur</td>
						<td scope="col">Genre</td>
						<td scope="col">Nb Exemplaires</td>
					</tr>
					<c:forEach var="livresRecherche" items="${ livresRecherche }">
							<tr>
								<td><a href="${contextPath}/description/${livresRecherche.id}">${ livresRecherche.nom }</a></td>
								<td><c:out value="${ livresRecherche.parution }"></c:out></td>
								<td><c:out value="${ livresRecherche.auteur }"></c:out></td>
								<td><c:out value="${ livresRecherche.genre }"></c:out></td>
								<td><c:out value="${ livresRecherche.nb_exemplaire }"></c:out></td>
							</tr>
					</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>