<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<div align="center">
					
					<table>
						<tr>
							<td>
								<table class="table table-mg">
									<tr><td scope="col">Nom</td><td scope="col"><c:out value="${ livre.nom }"></c:out></td></tr>
									<tr><td scope="col">Auteur</td><td scope="col"><c:out value="${ livre.auteur }"></c:out></td></tr>
									<tr><td scope="col">Date de Parution</td><td scope="col"><c:out value="${ livre.parution }"></c:out></td></tr>
									<tr><td scope="col">Date de d?but</td><td scope="col"><c:out value="${ exemplaire.debut }"></c:out></td></tr>
									<tr><td scope="col">Date de Fin</td><td scope="col"><c:out value="${ exemplaire.fin }"></c:out></td></tr>
								</table>
							</td>
							<td>
								<div class="col">${ livre.synopsis }</div>
							</td>
						</tr>
					</table>
					<div align="center">
						<c:if test="${peutEtreProlonger}">
							<c:if test="${not exemplaire.prolonge}">
								<div class="content-button"><a href="${contextPath}/prolonge/${exemplaire.id}">Prolonger</a></div>
							</c:if>
						</c:if>
					</div>
					<a href="${contextPath}/accueil"> Retour</a>
				  </div>
			</div>
		</div>		  
	</body>
</html>