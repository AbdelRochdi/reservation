<%@ include file="header.jsp"%>
<h1>Welcome to Admin</h1>



<h3>Today's reservation</h3>




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