<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					<div align="center">
						<h2>Prolongation Validée</h2>
						<table class="table">
							<tr>
								<td>Date de début</td>
								<td>Date de fin</td>
							</tr>
							<tr>
								<td>${debut}</td>
								<td>${fin}</td>
							</tr>
						</table>
					</div>
					
					<a href="${contextPath}/accueil"> Retour</a>
				  </div>
			</div>
		</div>		  
	</body>
</html>