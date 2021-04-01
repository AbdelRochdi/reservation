<%@ include file="header.jsp"%>

<h1>Users list</h1>

<table>
	<tr>
		<th>Prenom</th>
		<th>Nom</th>
		<th>Email</th>
		<th>Role</th>
		<th>Etat</th>
		<th>Action</th>
	</tr>

	<c:forEach items="${ users }" var="user">
		<tr>
			<td>${ user.firstName }</td>
			<td>${ user.lastName }</td>
			<td>${ user.email }</td>
			<td>${ user.role }</td>
			<td>${ user.state }</td>
			<td><a href="activateUser?id=${ user.id }"
				class="${ user.state == 'active' ? 'disable' : 'enable' }">${ user.state == 'active' ? 'D�sactiver' : 'Activer' }</a></td>
		</tr>
	</c:forEach>

</table>

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
