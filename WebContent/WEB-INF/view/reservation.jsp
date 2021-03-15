<%@ include file="header.jsp"%>

<h2>Welcome student</h2>

would you like to make a reservation for today ?

<form action="makeReservation" method = "post">
	<input type="submit" value="Réserver" onclick="if (!(confirm('are you sure about that ?'))) return false"/>
</form>




</body>
</html>