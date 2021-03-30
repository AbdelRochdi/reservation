<%@ include file="header.jsp"%>

<h2>Welcome ${ name }</h2>

would you like to make a reservation for tomorrow ?
<c:choose>
	<c:when test="${today == 'SATURDAY' || today == 'FRIDAY'}">
		<c:choose>
			<c:when test="${ empty weekend.type }">
				<form action="makeReservation" method="post">
					<input type="hidden" name="type" value="week-end" /> <input
						type="submit" value="Réserver"
						onclick="if (!(confirm('are you sure about that ?'))) return false" />
				</form>
			</c:when>
			<c:otherwise>
				<form action="cancelReservation" method="post">
					<input type="hidden" name="type" value="week-end" /> <input
						type="submit" value="Annuler"
						onclick="if (!(confirm('are you sure about that ?'))) return false" />
				</form>
			</c:otherwise>
		</c:choose>
	</c:when>

	<c:otherwise>
		<c:choose>
			<c:when test="${ empty matin.type }">
				<form action="makeReservation" method="post">
					<input type="hidden" name="type" value="matin" /> <input
						type="submit" value="Réserver le matin"
						onclick="if (!(confirm('are you sure about that ?'))) return false" />
				</form>
			</c:when>
			<c:otherwise>
				<form action="cancelReservation" method="post">
					<input type="hidden" name="type" value="matin" /> <input
						type="submit" value="Annuler le matin"
						onclick="if (!(confirm('are you sure about that ?'))) return false" />
				</form>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${ empty soir.type  }">
				<form action="makeReservation" method="post">
					<input type="hidden" name="type" value="soir" /> <input
						type="submit" value="Réserver le soir"
						onclick="if (!(confirm('are you sure about that ?'))) return false" />
				</form>
			</c:when>
			<c:otherwise>
				<form action="cancelReservation" method="post">
					<input type="hidden" name="type" value="soir" /> <input
						type="submit" value="Annuler le soir"
						onclick="if (!(confirm('are you sure about that ?'))) return false" />
				</form>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>

<h3>Today active reservation List</h3>

<section class="reservations">
	<c:choose>
		<c:when test="${today == 'SATURDAY' || today == 'FRIDAY' && now >= 20 || today == 'SUNDAY' && now < 20 }">
			<table>
				<tr>
					<th>Prenom</th>
					<th>Nom</th>
					<th>Date</th>
					<th>Type</th>
					<th>State</th>
					<th>Presence</th>
				</tr>

				<c:forEach items="${ now >= 20 ? tomorrowWeekendActiveReservations : todayWeekendActiveReservations }"
					var="reservation">
					<tr>
						<td>${ reservation.user.firstName }</td>
						<td>${ reservation.user.lastName }</td>
						<td>${ reservation.date }</td>
						<td>${ reservation.type }</td>
						<td>${ reservation.state }</td>
						<td>${ reservation.presence }</td>
					</tr>
				</c:forEach>

			</table>
		</c:when>
		<c:otherwise>
			<table>
				<tr>
					<th>Prenom</th>
					<th>Nom</th>
					<th>Date</th>
					<th>Type</th>
					<th>State</th>
					<th>Presence</th>
				</tr>

				<c:forEach items="${now >= 20 ? tomorrowMatinActiveReservations : todayMatinActiveReservations }"
					var="reservation">
					<tr>
						<td>${ reservation.user.firstName }</td>
						<td>${ reservation.user.lastName }</td>
						<td>${ reservation.date }</td>
						<td>${ reservation.type }</td>
						<td>${ reservation.state }</td>
						<td>${ reservation.presence }</td>
					</tr>
				</c:forEach>

			</table>
			<table>
				<tr>
					<th>Prenom</th>
					<th>Nom</th>
					<th>Date</th>
					<th>Type</th>
					<th>State</th>
					<th>Presence</th>
				</tr>

				<c:forEach items="${now >= 20 ? tomorrowSoirActiveReservations : todaySoirActiveReservations }"
					var="reservation">
					<tr>
						<td>${ reservation.user.firstName }</td>
						<td>${ reservation.user.lastName }</td>
						<td>${ reservation.date }</td>
						<td>${ reservation.type }</td>
						<td>${ reservation.state }</td>
						<td>${ reservation.presence }</td>
					</tr>
				</c:forEach>

			</table>
		</c:otherwise>
	</c:choose>


</section>


<h2>Historique de reservations</h2>

<table>
	<tr>
		<th>Date</th>
		<th>Type</th>
		<th>State</th>

	</tr>

	<c:forEach items="${ reservations }" var="reservation">
		<tr>
			<td>${ reservation.date }</td>
			<td>${ reservation.type }</td>
			<td>${ reservation.state }</td>
		</tr>
	</c:forEach>

</table>




</body>
</html>