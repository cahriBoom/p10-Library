<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
    <head>
    	<%@ include file="header.jsp" %>
    </head>
    <body>
		<div class="container" align="center">
				<div>
				<form:form method="post" modelAttribute="client" action="login">
			    	<div class="box">
			        	<h3>Bibliothèque - Connexion</h3>
			        	<c:if test="${not empty msg}">
					            <div class="msg">${msg}</div>
					        </c:if>
		        		<div class="table-mg">
							<div class="form-group row">
								<label for="mail">Email</label>
								<div class="col-sm-10">
									<form:input class="form-control" path="username" type="text" id="mail" size="20" maxlength="60" />
								</div>
							</div>
							<div class="form-group row">
								<label for="inputPassword">Password</label>
								<div class="col-sm-10">
									<form:input class="form-control" path="password" type="password" autocomplete="off" id="password" size="20" maxlength="20" />
								</div>
							</div>
							<br />			                
					        <input type="submit" value="login" class="btn btn-primary mb-2" />
					        <br />
					        <div class="content-button" align="center">
					           <a href="./accueil"> Retour</a>
					        </div>
						</div>
			        </div>
			    </form:form>
			</div>
		</div>
    </body>
</html>