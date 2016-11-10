<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Affichage de l'heure par la jsp</title>
</head>
<body>

<!-- en language java : -->
<%
//out.println(request.getAttribute("heureActuelle"));
%>

<!-- en language EL : -->

${heureActuelle}

</body>
</html>