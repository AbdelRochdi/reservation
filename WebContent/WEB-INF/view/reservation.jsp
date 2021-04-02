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
			<li class="profile">${ user.firstName } ${ user.lastName }</li>
			<li><a href="logout" class="logout">Logout</a></li>
		</ul>
		
		

	</header>

<c:if test="${ now < 9 &&  now >= 20 }">

	<h3>Fin des réservations pour ajourd'hui, revenez demain ?</h3>

</c:if>


<c:if test="${ now >= 9 &&  now < 20 }">
<h3>Voulez vous reservez pour demain ?</h3>
<section class="makeRes">
<c:choose>
	<c:when test="${today == 'SATURDAY' || today == 'FRIDAY'}">
	<div>
		<h4 class="reservation-header">De 9h à 20h</h4><br />
		<c:choose>
			<c:when test="${ empty weekend.type }">
				<form action="makeReservation" method="post">
					<input type="hidden" name="type" value="week-end" /> <input
						type="submit" value="Réserver le week-end" class="limiter" style="padding: 0.4rem; margin: 0.4rem"
						onclick="if (!(confirm('Etes-vous sur de vouloir resérver ?'))) return false" />
				</form>
			</c:when>
			<c:otherwise>
				<form action="cancelReservation" method="post">
					<input type="hidden" name="type" value="week-end" /> <input
						type="submit" value="Annuler le week-end" class="annuler" style="padding: 0.4rem; margin: 0.4rem"
						onclick="if (!(confirm('Etes-vous sur de vouloir annuler ?'))) return false" />
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	</c:when>

	<c:otherwise>
	<div class="resChoices">
	<div>
	<h4 class="reservation-header">De 9h à 17h</h4><br />
		<c:choose>
			<c:when test="${ empty matin.type }">
				<form action="makeReservation" method="post">
					<input type="hidden" name="type" value="matin" /> <input
						type="submit" value="Réserver le matin" class="limiter" style="padding: 0.4rem; margin: 0.4rem"
						onclick="if (!(confirm('Etes-vous sur de vouloir resérver ?'))) return false" />
				</form>
			</c:when>
			<c:otherwise>
				<form action="cancelReservation" method="post">
					<input type="hidden" name="type" value="matin" /> <input
						type="submit" value="Annuler le matin" class="annuler" style="padding: 0.4rem; margin: 0.4rem"
						onclick="if (!(confirm('Etes-vous sur de vouloir annuler ?'))) return false" />
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	<div>
	<h4 class="reservation-header">De 17h à 20h</h4><br />
		<c:choose>
			<c:when test="${ empty soir.type  }">
				<form action="makeReservation" method="post">
					<input type="hidden" name="type" value="soir" /> <input
						type="submit" value="Réserver le soir" class="limiter" style="padding: 0.4rem; margin: 0.4rem"
						onclick="if (!(confirm('Etes-vous sur de vouloir resérver ?'))) return false" />
				</form>
			</c:when>
			<c:otherwise>
				<form action="cancelReservation" method="post">
					<input type="hidden" name="type" value="soir" /> <input
						type="submit" value="Annuler le soir" class="annuler" style="padding: 0.4rem; margin: 0.4rem"
						onclick="if (!(confirm('Etes-vous sur de vouloir annuler ? ?'))) return false" />
				</form>
			</c:otherwise>
		</c:choose>
		</div>
		</div>
	</c:otherwise>
</c:choose>
</section>
</c:if>


<c:choose>
<c:when test="${ now < 20 }">
<h3>Liste Finale d'aujourd'hui</h3>
</c:when>
<c:otherwise>
<h3>Liste Finale de demain</h3>
</c:otherwise>
</c:choose>


<section class="reservations">

	<c:choose>
	
		<c:when test="${today == 'SATURDAY' || today == 'FRIDAY' && now >= 20 || today == 'SUNDAY' && now < 20 }">
		<div>
		<h3 class="tableHeader"> Weekend</h3>
			<table>
				<tr>
					<th>Prenom</th>
					<th>Nom</th>
					<th>Date</th>
					<th>Type</th>
					<th>State</th>
				</tr>

				<c:forEach items="${ now >= 20 ? tomorrowWeekendActiveReservations : todayWeekendActiveReservations }"
					var="reservation">
					<tr>
						<td>${ reservation.user.firstName }</td>
						<td>${ reservation.user.lastName }</td>
						<td>${ reservation.date }</td>
						<td>${ reservation.type }</td>
						<td>${ reservation.state }</td>
					</tr>
				</c:forEach>

			</table>
			</div>
		</c:when>
		<c:otherwise>
		<div>
		<h3 class="tableHeader"> Matin</h3>
			<table>
				<tr>
					<th>Prenom</th>
					<th>Nom</th>
					<th>Date</th>
					<th>Type</th>
					<th>State</th>
				</tr>

				<c:forEach items="${now >= 20 ? tomorrowMatinActiveReservations : todayMatinActiveReservations }"
					var="reservation">
					<tr>
						<td>${ reservation.user.firstName }</td>
						<td>${ reservation.user.lastName }</td>
						<td>${ reservation.date }</td>
						<td>${ reservation.type }</td>
						<td>${ reservation.state }</td>
					</tr>
				</c:forEach>

			</table>
			</div>
			<div>
			<h3 class="tableHeader"> Soir</h3>
			<table>
				<tr>
					<th>Prenom</th>
					<th>Nom</th>
					<th>Date</th>
					<th>Type</th>
					<th>State</th>
				</tr>

				<c:forEach items="${now >= 20 ? tomorrowSoirActiveReservations : todaySoirActiveReservations }"
					var="reservation">
					<tr>
						<td>${ reservation.user.firstName }</td>
						<td>${ reservation.user.lastName }</td>
						<td>${ reservation.date }</td>
						<td>${ reservation.type }</td>
						<td>${ reservation.state }</td>
					</tr>
				</c:forEach>

			</table>
			</div>
		</c:otherwise>
	</c:choose>


</section>


<h3>Historique de reservations</h3>

<div>
<table>
	<tr>
		<th>Date</th>
		<th>Type</th>
		<th>State</th>
		<th>Presence</th>
	</tr>

	<c:forEach items="${ reservations }" var="reservation">
		<tr>
			<td>${ reservation.date }</td>
			<td>${ reservation.type }</td>
			<td>${ reservation.state }</td>
			<td>${ reservation.presence }</td>
		</tr>
	</c:forEach>

</table>

<%@ include file="footer.jsp"%>
