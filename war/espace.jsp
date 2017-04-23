
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Espace</title>
</head>
<body>
<div class="container">
<h1 class="text-center">Page Espace</h1>
<p> Bonjour <%=request.getAttribute("nom")%>: <br/>
Bienvenu sur votre dashboard vous pourrez ici consulter vos projets les gerer.</p>


<%=request.getAttribute("type")%>

</div>
</body>
</html>