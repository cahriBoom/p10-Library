<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    	<%@ include file="header.jsp" %>
    </head>
    <body>
		<div id="container">
			<%@ include file="menu.jsp" %>
			<div id="contenu">
				<h2>Liste des Emprunts</h2>
					<div align="center" class="table-mg">
					    <div class="table" align="center">
					    <form action="/consultEmprunt" method="post">
						    <h2><c:out value="${ message }"></c:out></h2>
						    <table>
						    	<tr>
								    <td scope="col">Nom</td>
								    <td scope="col">Identifiant</td>
								    <td scope="col">Auteur</td>
									<td scope="col">Date de début</td>
									<td scope="col">Date de fin</td>
									<td scope="col">Rendre le livre</td>
								</tr>
								<c:forEach var="allEmprunt" items="${ allEmprunt }">
								<tr>
									<td><c:out value="${ allEmprunt.livre.nom }" /></td>
									<td><c:out value="${ allEmprunt.id }" /></td>
									<td><c:out value="${ allEmprunt.livre.auteur }" /></td>
									<td><c:out value="${ allEmprunt.debut }" /></td>
									<td><c:out value="${ allEmprunt.fin }" /></td>
									<td><a href="${contextPath}/returnBookVerification/${allEmprunt.id}">Retourner</a></td>
								</tr>
								</c:forEach>
						    </table>
						</form>
									    
					    <div class="content-button">
					    	<a href="${contextPath}/welcome/${current}">Retour</a>
					    </div>
					</div>
			    </div>
			</div>
		</div>
		<%@ include file="footer.jsp" %>
    </body>
</html>