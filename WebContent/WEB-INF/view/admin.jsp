<%@ include file="header.jsp"%>
<h1>Welcome to Admin</h1>


<h3>Reservation limit</h3>

<form action="setLimit" method="post">
<input type="date" name="date" />
	<input type="number" name="limit" placeholder="limit" />
	<select name="types" id="">
		<option value="matin">Matin</option>
		<option value="soir">Soir</option>
		<option value="week-end">Week-end</option>
	</select>
	<input type="submit" value="Envoyer" />

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

<h3>Today active reservation List</h3>

<table>
	<tr>
		<th>Prenom</th>
		<th>Nom</th>
		<th>Date</th>
		<th>Type</th>
		<th>State</th>
	</tr>

	<c:forEach items="${ todayActiveReservations }" var="reservation">
		<tr>
			<td>${ reservation.user.firstName }</td>
			<td>${ reservation.user.lastName }</td>
			<td>${ reservation.date }</td>
			<td>${ reservation.type }</td>
			<td>${ reservation.state }</td>
		</tr>
	</c:forEach>

</table>

<h3>Today reservation List</h3>

<table>
	<tr>
		<th>Prenom</th>
		<th>Nom</th>
		<th>Date</th>
		<th>Type</th>
		<th>State</th>
	</tr>

	<c:forEach items="${ todayReservations }" var="reservation">
		<tr>
			<td>${ reservation.user.firstName }</td>
			<td>${ reservation.user.lastName }</td>
			<td>${ reservation.date }</td>
			<td>${ reservation.type }</td>
			<td>${ reservation.state }</td>
		</tr>
	</c:forEach>

</table>

<h3>Users reservation History</h3>

<input type="text" placeholder="Search" />
<input type="submit" value="Search"/>


<table>
	<tr>
		<th>Prenom</th>
		<th>Nom</th>
		<th>Date</th>
		<th>Type</th>
		<th>State</th>
	</tr>

	<c:forEach items="${ reservations }" var="reservation">
		<tr>
			<td>${ reservation.user.firstName }</td>
			<td>${ reservation.user.lastName }</td>
			<td>${ reservation.date }</td>
			<td>${ reservation.type }</td>
			<td>${ reservation.state }</td>
		</tr>
	</c:forEach>

</table>


<h3>Users list</h3>

<table>
	<tr>
		<th>Prenom</th>
		<th>Nom</th>
		<th>Email</th>
		<th>Role</th>
		<th>State</th>
		<th>Action</th>
	</tr>

	<c:forEach items="${ users }" var="user">
		<tr>
			<td>${ user.firstName }</td>
			<td>${ user.lastName }</td>
			<td>${ user.email }</td>
			<td>${ user.role }</td>
			<td>${ user.state }</td>
			<td><a href="activateUser?id=${ user.id }">${ user.state == 'active' ? 'Désactiver' : 'Activer' }</a></td>
		</tr>
	</c:forEach>

</table>

</body>
</html>