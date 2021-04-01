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
<body class="admin">

	<header class="header">

		<img src="${ pageContext.request.contextPath }/resources/logo.png"
			alt="logo" class="logo">
		<ul class="menu">
			<li><a href="admin">Liste Active</a></li>
			<li><a href="adminReservations">Reservations</a></li>
			<li><a href="users">Utilisateurs</a></li>
			<li><a href="logout" class="logout">Se Déconnecter</a></li>
		</ul>




	</header>