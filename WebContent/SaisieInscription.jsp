<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/MenuInscription.jsp"></jsp:include>


<form action="SaisieInscriptionControler" method="post">
	<div>
        <label for="nom">Nom :</label>
        <input type="text" value="${param.nom}" name="nom"/>
    </div>
    	<div>
        <label for="age">Age :</label>
        <input type="text" value="${param.age}" name="age"/>
    </div>
   <c:if test="${not empty paysDeMaListe}">
    <div>
  	  <select name="selectPays" size=1>
  	  	<c:forEach var="pays" items="${paysDeMaListe}">
  	  	  	<option value="${pays}">${pays}</option>
  	  	</c:forEach>
  	  </select>
    </div>
    </c:if>
    <c:if test="${empty paysDeMaListe}">
    <div>
    Il faut lancer depuis la servlet ! 
    </div>
    </c:if>
    <div>
        <label for="courriel">Courriel :</label>
        <input type="email" value ="${param.courriel}" name="courriel" />
    </div>
    <div>
        <label for="message">Message :</label>
        <textarea name="message">${param.message}</textarea>
    </div>
<input type=submit value="créer" name="action"/>
<input type=submit value="déconnecter" name="action"/>
</form>

<br/><br/>
<span>${messageFormulaireEnregistre}</span>
<span>${messageErreur}</span>
<ul>
<c:forEach var="message d'erreur : "  items="${messageErreur}">
<li>${messageErreur}</li>
</c:forEach>
</ul>
<br/><br/>
<p>
Heure de première connexion : ${heurePremiereConnexion}
</p>
<br/><br/>
<p>
Heure de la dernière requête : ${heureDerniereRequete}
</p>
<br/><br/>
<p>
Nombre de personnes connectées actuellement : ${CompteurSession.valeur}
</p>
<br/><br/>
</body>
</html>