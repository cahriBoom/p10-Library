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
		<c:set var="current" value="${current}" scope="session" />
		<c:set var="client" value="${client}" scope="session" />
	 </div>
</body>
</html>