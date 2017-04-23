
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Espace</title>

<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.0.min.js"></script>
</head>
<body>
<div class="container">
<h1 class="text-center">Page Espace</h1>
<p> Bonjour <%=request.getAttribute("nom")%>: <br/>
Bienvenu sur votre dashboard vous pourrez ici consulter vos projets les gerer.</p>
<h2>Mes projets</h2>
<%=request.getAttribute("proj")%>

<div class="<%=request.getAttribute("block1")%>">

<h2>Ajout d' <%=request.getAttribute("text1")%></h2>
<div class="row"><div class="text-center container">
	<div class="col-md-6 col-md-offset-3">
	<form method="post" action="AddRequest">
   <div class="form-group">
    <label >Nom</label>
    <input type="text" class="hidden" name="iduser" value="<%=request.getAttribute("iduser")%>">
    <input type="text" class="hidden" name="type" value="<%=request.getAttribute("typep")%>">
    <input type="text" class="hidden" name="etat" value="soumis">
    <input type="text" class="form-control" name="nom" placeholder="nom">
    <label >Description</label>
    <input type="text" class="form-control" name="description" placeholder="description">
    <label class="<%=request.getAttribute("block3")%>" >Numero de projet</label>
    <input type="text" class="form-control <%=request.getAttribute("block3")%>" name="history" placeholder="description">
  </div>
  

  <div class="form-group">
    <label >Delai estimé:</label>
    <select class="form-control" name="delai">
    		
			<option label="court">Court</option>
			<option label="moyen">Moyen</option> 
			<option label="long">Long</option> 
		</select>
  </div>
  
  <div class="form-group">
    <label >Coût</label>
    <select class="form-control" name="cout">
    		
			<option label="court">Faible</option>
			<option label="moyen">Moyen</option> 
			<option label="long">Elevée</option> 
		</select>
  </div>
   
  <button type="submit" class="btn btn-default btn-primary">Ajout</button>
  
</form>
</div></div></div>
</div>
<div class="<%=request.getAttribute("block2")%>">

<h2>Liste des <%=request.getAttribute("text2")%> disponibles</h2>
<table class="table">
    <thead>
      <tr>
        <th>Nom</th>
        <th>Description</th>
        <th>Delai</th>
        <th>Cout</th>
      </tr>
    </thead>
    <tbody>
<%=request.getAttribute("tabletache")%>
	</tbody>
</div>

</div>
<script>
		
		var checkecoute=0;
		
function ecoute(){
			if (checkecoute==0){
			$('.knowmore').removeClass("hidden ");
			
			checkecoute=1;
			}
			else{
			$('.knowmore').addClass("hidden ");
			
			checkecoute=0;
			}
};
</script>
</body>
</html>