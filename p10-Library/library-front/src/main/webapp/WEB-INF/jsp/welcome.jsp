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
			<div id="header">
			</div>
			<%@ include file="menu.jsp" %>
			<div id="contenu">
  				<div class="table-mg content-button" align="center">            
					<h2>Bibliothèque</h2>
					<h2>Bienvenue sur le site internet, ${client.prenom}</h2> <br /> 
					<a href="${contextPath}/consultEmprunt/${ client.mail }">Emprunts & Réservations</a> <br /> <br />
					<c:if test="${client.admin}">
						<a href="${contextPath}/returnBook">Retourner Livres</a> <br /> <br />		
					</c:if>
				</div>
				<div class="content-button" align="center">	
					<a href="${contextPath}/accueil"> Retour Accueil</a>
				</div>
			</div>
		</div>
    </body>
</html>