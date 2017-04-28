
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
	<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.0.min.js"></script>
<title>Detail</title>
</head>
<body>
<div class="container">

<h1 class="text-center"><%=request.getAttribute("titreprojet")%></h1>
<div class="row">
<div class="col-md-3 col-md-offset-3" >
<p>Le projet s'effectura sur un delai <%=request.getAttribute("delaiprojet")%></p>
</div>
<div class="col-md-3" >
<p>Le client dispose de <%=request.getAttribute("coutprojet")%> ressources </p> 
</div>
</div>
<div class="row">
<p></p><%=request.getAttribute("descriptionprojet")%>
</div>


<div class="<%=request.getAttribute("testetatp")%>" > le projet est actuellement soumis mais pas pris en charge</div>

</div>
</body>
</html>