<%@page import="java.time.LocalDate"%>
<%@ include file="header.jsp"%>
<h1>Reservation limit</h1>
<section class="reservationLimit">
<form action="setLimit" method="post" class="limitForm">
	<input type="date" name="date" min="<%= LocalDate.now() %>" required="required"/> 
	<input type="number" name="limit" placeholder="Limit" required="required"/> 
		<select name="types" id="">
		<option value="matin">Matin</option>
		<option value="soir">Soir</option>
		<option value="week-end">Week-end</option>
	</select> <input type="submit" value="Limiter" class="limiter" />

</form>
<table class="rlTable">
	<tr>
		<th>Date</th>
		<th>Limite de places</th>
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
</section>





<h1>Liste de reservations de demain</h1>
<section class="reservations">
	<c:choose>
		<c:when test="${today == 'SATURDAY' || today == 'FRIDAY'}">
			<table>
				<tr>
					<th>Prenom</th>
					<th>Nom</th>
					<th>Date</th>
					<th>Type</th>
					<th>Etat</th>
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
					<th>Etat</th>
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
					<th>Etat</th>
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

