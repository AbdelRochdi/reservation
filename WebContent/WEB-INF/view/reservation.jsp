<%@ include file="header.jsp"%>

<h2>Welcome ${ name }</h2>

would you like to make a reservation for today ?

<form action="makeReservation" method = "post">
	<input type="submit" value="Réserver" onclick="if (!(confirm('are you sure about that ?'))) return false"/>
</form>

<h2> Historique de reservations</h2>

<table>
	<tr>
		<th>Id</th>
		<th>Date</th>
	</tr>

	<c:forEach items="${ reservations }" var="reservation">
		<tr>
			<td>${ reservation.reservationId }</td>
			<td>${ reservation.date }</td>
		</tr>
	</c:forEach>

</table>




</body>
</html>