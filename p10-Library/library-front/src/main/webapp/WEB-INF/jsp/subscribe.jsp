<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
    	<%@ include file="header.jsp" %>
    	<link rel="stylesheet" href="login.css"/>
    </head>
		<body>
			<div class="container" align="center">
				<div class="logindiv">
					<form:form id="registerform" method="post" modelAttribute="client" action="subscribe">
			    		<div class="box">
			                <h3>Bibliothèque - Inscription</h3>
			                <c:if test="${not empty error}">
	           					<div class="error">${error}</div>
						    </c:if>
						    <c:if test="${not empty msg}">
					            <div class="msg">${msg}</div>
					        </c:if>
			                                
			                <div class="form-group">
				                <label for="nom">Nom</label>
				                <form:input path="nom" type="text" id="nom" name="nom" class="form-control" required="required" placeholder="Entrer Nom"/>
				                
				                <label for="prenom">Prenom</label>
				                <form:input path="prenom" type="text" value="" id="prenom" class="form-control" required="required" placeholder="Entrer Prenom" />			                
				                
				                <label for="mail">Mail</label>
				                <form:input path="mail" type="text" value="" id="mail" class="form-control" required="required" placeholder="Entrer Mail" />
				
				                <label for="password">Mot de passe</label>
				                <form:input type="password" class="form-control" placeholder="Password" path="password" required="required" id="password" autocomplete="off" />
				                <br />
								
				                <button class="btn btn-primary" type="submit">Inscription</button>
				                <br />
			                </div>
			                 <div class="content-button" align="center">
					           <a href="./accueil"> Retour Accueil</a>
					        </div>
			            </div>
			        </form:form>
				</div>
    		</div>
    </body>
</html>