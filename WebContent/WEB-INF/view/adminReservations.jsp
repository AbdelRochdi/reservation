<%@ include file="header.jsp"%>

<h3>Reservation limit</h3>

<form action="setLimit" method="post">
	<input type="date" name="date" /> <input type="number" name="limit"
		placeholder="limit" /> <select name="types" id="">
		<option value="matin">Matin</option>
		<option value="soir">Soir</option>
		<option value="week-end">Week-end</option>
	</select> <input type="submit" value="Envoyer" />

</form>
<table>
	<tr>
		<th>Date</th>
		<th>Reservation limit</th>
		<th>Type</th>
	</tr>

	<c:forEach items="${ reservationLimits }" var="reservationLimit">
		<tr>
			<td>${ reservationLimit.date }</td>
			<td>${ reservationLimit.reservationLimit }</td>
			<td>${ reservationLimit.type }</td>
		</tr>
	</c:forEach>

</table>

<h3>Today reservation List</h3>
<section class="reservations">
	<c:choose>
		<c:when test="${today == 'SATURDAY' || today == 'FRIDAY'}">
			<table>
				<tr>
					<th>Prenom</th>
					<th>Nom</th>
					<th>Date</th>
					<th>Type</th>
					<th>State</th>
				</tr>

				<c:forEach items="${ todayWeekendReservations }" var="reservation">
					<tr>
						<td>${ reservation.user.firstName }</td>
						<td>${ reservation.user.lastName }</td>
						<td>${ reservation.date }</td>
						<td>${ reservation.type }</td>
						<td>${ reservation.state }</td>
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
				</tr>

				<c:forEach items="${ todayMatinReservations }" var="reservation">
					<tr>
						<td>${ reservation.user.firstName }</td>
						<td>${ reservation.user.lastName }</td>
						<td>${ reservation.date }</td>
						<td>${ reservation.type }</td>
						<td>${ reservation.state }</td>
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
				</tr>

				<c:forEach items="${ todaySoirReservations }" var="reservation">
					<tr>
						<td>${ reservation.user.firstName }</td>
						<td>${ reservation.user.lastName }</td>
						<td>${ reservation.date }</td>
						<td>${ reservation.type }</td>
						<td>${ reservation.state }</td>
					</tr>
				</c:forEach>

			</table>
		</c:otherwise>
	</c:choose>


</section>



<%@ include file="footer.jsp"%>

