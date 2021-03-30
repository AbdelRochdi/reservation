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

<h1>Connectez-vous </h1>

<form action="loginProcess" method="post" class="form">

<input type="text" name="email"  placeholder="Email" />

<input type="password" name="password" placeholder="Password" />

<input type="submit" value="Se connecter" style="height: 38px" class="login"/>

<h4>OU</h4>

<a href="register" class="register">S'enregister</a>
</form>


</section>



</body>
</html>