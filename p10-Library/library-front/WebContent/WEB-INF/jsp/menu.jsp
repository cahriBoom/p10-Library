<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />

<nav class="col navbar navbar-expand-lg navbar-light bg-light">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
		<li class="nav-item active">
			<a class="nav-link" href="${contextPath}/accueil">Accueil <span class="sr-only">(current)</span></a>
		</li>
		<c:if test = "${current != 'anonymousUser'}">
		<li class="nav-item">
			<a class="nav-link" href="${contextPath}/search">Rechercher</a>
		</li>
		</c:if>
		<c:if test="${client.admin}">
		<li class="nav-item">
			<a class="nav-link" href="${contextPath}/addlivres">Ajouter Livre</a>
		</li>
		</c:if>
    </ul>    
	<ul class="navbar-nav" id="right">
    	<c:set var = "current" scope = "session" value = "${ current }"/>
		<c:if test = "${current != 'anonymousUser'}">
			<li class="nav-item"><a class="nav-link" href="${contextPath}/welcome/${ current }"> ${ current }</a></li>
			<li class="nav-item"><a class="nav-link" href="${contextPath}/logout">Logout</a></li>
		</c:if>
		<c:if test = "${current == 'anonymousUser'}">
			<li class="nav-item"><a class="nav-link" href="${contextPath}/login">Connexion</a></li>
			<li class="nav-item"><a class="nav-link" href="${contextPath}/subscribe">Inscription</a></li>
		</c:if>
    </ul>
</div>
</nav>