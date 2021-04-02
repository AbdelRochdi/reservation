<%@ include file="header.jsp"%>

<h1>Historique de reservations des utilisateurs</h1>

<!-- <input type="text" placeholder="Search" />
<input type="submit" value="Search" /> -->

<table>
	<tr>
		<th>Prenom</th>
		<th>Nom</th>
		<th>Date</th>
		<th>Type</th>
		<th>Etat</th>
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

<%@ include file="footer.jsp"%>
