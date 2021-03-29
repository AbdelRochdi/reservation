<%@ include file="header.jsp"%>

<h2>Welcome ${ name }</h2>

would you like to make a reservation for today ?
<c:choose>
	<c:when test="${today == 'SATURDAY' || today == 'SUNDAY'}">
		<form action="makeReservation" method="post">
			<input type="hidden" name="type" value="week-end" /> <input
				type="submit" value="Réserver"
				onclick="if (!(confirm('are you sure about that ?'))) return false" />
		</form>
	</c:when>
	<c:otherwise>
		<form action="makeReservation" method="post">
			<input type="hidden" name="type" value="matin" /> <input
				type="submit" value="Réserver le matin"
				onclick="if (!(confirm('are you sure about that ?'))) return false" />
		</form>
		<form action="makeReservation" method="post">
			<input type="hidden" name="type" value="soir" /> <input
				type="submit" value="Réserver le soir"
				onclick="if (!(confirm('are you sure about that ?'))) return false" />
		</form>
	</c:otherwise>
</c:choose>


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