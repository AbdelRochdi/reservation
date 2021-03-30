<%@ include file="header.jsp"%>
<h1>Welcome to Admin</h1>

<h3>Today active reservation List</h3>

<section class="activeReservations">
	<c:choose>
		<c:when test="${today == 'SATURDAY' || today == 'FRIDAY'}">
			<table>
				<tr>
					<th>Prenom</th>
					<th>Nom</th>
					<th>Date</th>
					<th>Type</th>
					<th>State</th>
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
			<table>
				<tr>
					<th>Prenom</th>
					<th>Nom</th>
					<th>Date</th>
					<th>Type</th>
					<th>State</th>
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
			<table>
				<tr>
					<th>Prenom</th>
					<th>Nom</th>
					<th>Date</th>
					<th>Type</th>
					<th>State</th>
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

