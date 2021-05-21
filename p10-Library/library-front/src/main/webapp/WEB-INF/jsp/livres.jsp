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
	<div align="center">
	<%@ include file="menu.jsp" %>
		<table>
			<tr>
				<td>ID</td>
				<td>Nom</td>
			</tr>
			<c:forEach var="livres" items="${ livres }">
					<tr>
						<td><c:out value="${ livres.id }"></c:out></td>
						<td><a href="${contextPath}/description/${ livres.id }"><c:out value="${ livres.nom }"></c:out></a></td>
					</tr>
			</c:forEach>
		</table>
	  </div>
</body>
</html>