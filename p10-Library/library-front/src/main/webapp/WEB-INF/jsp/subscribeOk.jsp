<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    	<%@ include file="header.jsp" %>
    </head>
	<body>
		<div class="container">
		<%@ include file="menu.jsp" %>
		<h1>Inscription Validee</h1>
			<div class="content-button" align="center">
				<a href="${contextPath}/accueil">Retour à l'Accueil</a>
			</div>
			<%@ include file="footer.jsp" %>
		</div>
	</body>
</html>