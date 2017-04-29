
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
<div id="visuProj">
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
</div>
<%=request.getAttribute("editproj")%>



<div class="<%=request.getAttribute("testetatp")%>" > <p>le projet est actuellement soumis mais pas pris en charge</p></div>

<div class="row <%=request.getAttribute("testetatn")%>">
<p>le projet est actuellement pris en charge par <%=request.getAttribute("moalead")%> </p>



<table class="table">
    <thead>
      <tr>
        <th>Nom</th>
        <th>Description</th>
        <th>Delai</th>
        <th>Cout</th>
        <th>Etat</th>
      </tr>
    </thead>
    <tbody>
<%=request.getAttribute("tabletache")%>
	</tbody>
	</table>
	
<p class="text-center <%=request.getAttribute("moaajout")%>"><span onclick="ajout()" class='btn'> Ajout d'une tache</span></p>
	
<div class="ajouttache hidden">
	<form method="post" action="AddRequest">
   <div class="form-group">
    <label >Nom</label>
    <input type="text" class="hidden" name="iduser" value="<%=request.getAttribute("iduser")%>">
    <input type="text" class="hidden" name="mail" value="<%=request.getAttribute("maill")%>">
    <input type="text" class="hidden" name="from" value="add">
    
    <input type="text" class="hidden" name="type" value="tache">
    <input type="text" class="hidden" name="etat" value="soumis">
    <input type="text" class="form-control" name="nom" placeholder="nom">
    <label >Description</label>
    <input type="text" class="form-control" name="description" placeholder="description">
    
    <input type="text" class="form-control hidden" name="ref" placeholder="IDprojet" value="<%=request.getAttribute("IDprojet")%>">
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
    <label >Cout</label>
    <select class="form-control" name="cout">
    		
			<option label="court">Faible</option>
			<option label="moyen">Moyenne</option> 
			<option label="long">Elevée</option> 
		</select>
  </div>
   
  <button type="submit" class="btn btn-default btn-primary">Ajout</button>
  
</form>
</div>
</div>




</div>
<script>
		
		var checkajout=0;
		
function ajout(){
			if (checkajout==0){
			$('.ajouttache').removeClass("hidden ");
			
			checkajout=1;
			}
			else{
			$('.ajouttache').addClass("hidden ");
			
			checkajout=0;
			}
};
</script>
<script>
		
		var checkedit=1;
		
function edit(){
			if (checkedit==0){
			$('#visuProj').removeClass("hidden ");
			$('#editProj').addClass("hidden ");
			checkedit=1;
			}
			else{
			$('#visuProj').addClass("hidden ");
			$('#editProj').removeClass("hidden ");			
			checkedit=0;
			}
};
</script>
</body>
</html>