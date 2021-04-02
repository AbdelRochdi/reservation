<%@ include file="header.jsp"%>

<h1>Liste des utilisateurs</h1>

<table>
	<tr>
		<th>Prenom</th>
		<th>Nom</th>
		<th>Email</th>
		<th>Role</th>
		<th>Etat</th>
		<th>Action</th>
		<th>Presence</th>
		<th>Absence</th>
	</tr>

	<c:forEach items="${ users }" var="user">
		<tr>
			<td>${ user.firstName }</td>
			<td>${ user.lastName }</td>
			<td>${ user.email }</td>
			<td>${ user.role }</td>
			<td>${ user.state }</td>
			<td><a href="activateUser?id=${ user.id }"
				class="${ user.state == 'active' ? 'disable' : 'enable' }">${ user.state == 'active' ? 'Désactiver' : 'Activer' }</a></td>
			<td>${ user.userReputation.presence }</td>
			<td>${ user.userReputation.absence }</td>
		</tr>
	</c:forEach>

</table>

<%@ include file="footer.jsp"%>
