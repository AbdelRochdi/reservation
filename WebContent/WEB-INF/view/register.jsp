<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ReservationApp</title>
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/resources/style.css">
</head>
<body class="index">

	<header class="header">

		<img src="${ pageContext.request.contextPath }/resources/logo.png"
			alt="logo" class="logo" />

	</header>


	<section class="loginSection">

		<h1>Inscrivez-vous</h1>

		<form:form action="processForm" modelAttribute="users" method="post"
			class="form">

			<form:input type="text" path="firstName" placeholder="Prenom" />
			<form:errors path="firstName" cssClass="error" />
			<form:input type="text" path="lastName" placeholder="Nom" />
			<form:errors path="lastName" cssClass="error" />
			
			<form:input type="text" path="email" placeholder="Email" />
			<c:if test="${ !empty messageEmail }"><p class="error">${ messageEmail }</p></c:if>
			<form:errors path="email" cssClass="error" />
			
			<form:input type="hidden" path="role" value="apprenant" />
			<form:input type="hidden" path="state" value="inactive" />
			<form:input type="password" path="password"	placeholder="Mot de passe" />
			<form:errors path="password" cssClass="error" />
		
			<input type="password" name="confirm" placeholder="Confirmez le mot de passe" />
			<c:if test="${ !empty message }"><p class="error">${ message }</p></c:if>
			<input type="submit" value="S'inscrire" class="login" style="height: 38px" />

			<h4>OU</h4>

			<a href=home class="register">Se connecter</a>
		</form:form>

	</section>



</body>
</html>