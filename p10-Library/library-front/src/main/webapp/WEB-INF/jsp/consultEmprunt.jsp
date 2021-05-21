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
								    <td scope="col">Auteur</td>
									<td scope="col">Date de début</td>
									<td scope="col">Date de fin</td>
								</tr>
								<c:forEach var="exemplaire" items="${ exemplaire }">
								<tr>
									<td><a href="${contextPath}/information/${exemplaire.id}"><c:out value="${ exemplaire.livre.nom }" /></a></td>
									<td><c:out value="${ exemplaire.livre.auteur }" /></td>
									<td><c:out value="${ exemplaire.debut }" /></td>
									<td><c:out value="${ exemplaire.fin }" /></td>
								</tr>
								</c:forEach>
						    </table>
						</form>
						
						
						<h2>Liste des Réservations</h2>
						<div align="center" class="table-mg">
						    <div class="table" align="center">
						    
						    	<table>
							    	<tr>
									    <td scope="col">Nom</td>
									    <td scope="col">Auteur</td>
									    <td scope="col">Annuler</td>

									</tr>
									<c:forEach var="livre_reserve" items="${ livre_reserve }">
										<tr>
											<td><a href="${contextPath}/information/${livre_reserve.id}"><c:out value="${ livre_reserve.nom }" /></a></td>
											<td><c:out value="${ livre_reserve.auteur }" /></td>
											<td><a href="${contextPath}/cancelReservation/${livre_reserve.id}&${current}">Annuler</a></td>
											
										</tr>
									</c:forEach>
						    	</table>
						    
						    
						    </div>
					    </div>
					    
					    
					    
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