<%@ include file="header.jsp"%>

<h2>Fill the form to register</h2>

<form:form action="processForm" modelAttribute = "users"  method="post" class="add_user">

<form:input type="text" path="firstName" placeholder="Prenom"/>
<form:input type="text" path="lastName" placeholder="Nom"/>
<form:input type="text" path="email" placeholder="Email"/>
<form:input type="hidden" path="role" placeholder="Email" value="apprenant"/>
<form:input type="hidden" path="state" placeholder="Email" value="inactive"/>
<form:input type="password" path="password" placeholder="Mot de passe"/>
<input type="password" placeholder="Confirmez le mot de passe"/>
<input type="submit" value ="S'inscrire" />

</form:form>


</body>
</html>