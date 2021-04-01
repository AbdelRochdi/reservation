<%@ include file="header.jsp"%>


<h1>Liste de reservations d'ajourd'hui</h1>

<section class="activeReservations">
	<c:choose>
		<c:when test="${today == 'SATURDAY' || today == 'FRIDAY'}">
		<h2>Week-end</h2>
			<table>
				<tr>
					<th>Prenom</th>
					<th>Nom</th>
					<th>Date</th>
					<th>Type</th>
					<th>Etat</th>
					<th>Presence</th>
					<th></th>
					<th></th>
				</tr>

				<c:forEach items="${ todayWeekendActiveReservations }"
					var="reservation">
					<tr>
						<td>${ reservation.user.firstName }</td>
						<td>${ reservation.user.lastName }</td>
						<td>${ reservation.date }</td>
						<td>${ reservation.type }</td>
						<td>${ reservation.state }</td>
						<td>${ reservation.presence }</td>
						<td><a
							href="markPresent?resid=${ reservation.reservationId }&userid=${reservation.user.id}"
							class="enable">Present</a></td>
						<td><a
							href="markAbsent?resid=${ reservation.reservationId }&userid=${reservation.user.id}"
							class="disable">Absent</a></td>
					</tr>
				</c:forEach>

			</table>
		</c:when>
		<c:otherwise>
		<h2>Matin</h2>
			<table>
				<tr>
					<th>Prenom</th>
					<th>Nom</th>
					<th>Date</th>
					<th>Type</th>
					<th>Etat</th>
					<th>Presence</th>
					<th></th>
					<th></th>
				</tr>

				<c:forEach items="${ todayMatinActiveReservations }"
					var="reservation">
					<tr>
						<td>${ reservation.user.firstName }</td>
						<td>${ reservation.user.lastName }</td>
						<td>${ reservation.date }</td>
						<td>${ reservation.type }</td>
						<td>${ reservation.state }</td>
						<td>${ reservation.presence }</td>
						<td><a
							href="markPresent?resid=${ reservation.reservationId }&userid=${reservation.user.id}"
							class="enable">Present</a></td>
						<td><a
							href="markAbsent?resid=${ reservation.reservationId }&userid=${reservation.user.id}"
							class="disable">Absent</a></td>
					</tr>
				</c:forEach>

			</table>
			<h2>Soir</h2>
			<table>
				<tr>
					<th>Prenom</th>
					<th>Nom</th>
					<th>Date</th>
					<th>Type</th>
					<th>Etat</th>
					<th>Presence</th>
					<th></th>
					<th></th>
				</tr>

				<c:forEach items="${ todaySoirActiveReservations }"
					var="reservation">
					<tr>
						<td>${ reservation.user.firstName }</td>
						<td>${ reservation.user.lastName }</td>
						<td>${ reservation.date }</td>
						<td>${ reservation.type }</td>
						<td>${ reservation.state }</td>
						<td>${ reservation.presence }</td>
						<td><a
							href="markPresent?resid=${ reservation.reservationId }&userid=${reservation.user.id}"
							class="enable">Present</a></td>
						<td><a
							href="markAbsent?resid=${ reservation.reservationId }&userid=${reservation.user.id}"
							class="disable">Absent</a></td>
					</tr>
				</c:forEach>

			</table>
		</c:otherwise>
	</c:choose>


</section>

<%@ include file="footer.jsp"%>

