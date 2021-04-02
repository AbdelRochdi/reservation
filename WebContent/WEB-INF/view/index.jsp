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

		<img src="${ pageContext.request.contextPath }/resources/logo.png" alt="logo" class="logo"/>

	</header>



<section class="loginSection">

<c:if test="${!empty message }"> <p class="successMessage">${ message }</p> </c:if>

<h1>Connectez-vous </h1>

<form action="loginProcess" method="post" class="form">

<input type="text" name="email"  placeholder="Email" />
<c:if test="${ !empty noUserMessage }"><p class="error">${ noUserMessage }</p></c:if>
 

<input type="password" name="password" placeholder="Password" />
<c:if test="${ !empty passIncorrectMessage }"><p class="error">${ passIncorrectMessage }</p></c:if>
<c:if test="${ !empty inactifMessage }"><p class="error">${ inactifMessage }</p></c:if>

<input type="submit" value="Se connecter" style="height: 38px" class="login"/>

<h4>OU</h4>

<a href="register" class="register">S'enregister</a>
</form>


</section>



</body>
</html>