
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
	<link rel="stylesheet" href="style.css">
	<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.0.min.js"></script>
<title>Detail</title>
</head>
<body>
<div class="account " style="z-index:2;">


<div class="text-right" >
<a href="/index.html" style="margin-bottom: 10px;"><span  style="margin-bottom: 10px;"class='btn btn-danger glyphicon glyphicon-off' aria-hidden="true"></span></a>
<form method="POST" action="connectUser">
<input type="text" class="hidden"name="mail" value="<%=request.getAttribute("maill")%>">
<input type='text' class='hidden' name='from' value='visu'>
<button  style="margin-bottom: 10px;" type="submit" class='btn btn-info glyphicon glyphicon-tower' aria-hidden="true"></button></form>
<span onclick="acc()" class= 'btn btn-warning glyphicon glyphicon-user' aria-hidden="true"></span></div>
<div id="form_acount" class="hidden text-center">
<form method="post" action="ModificationCompte" style='background:#FFF;padding: 10px;'>
					<input type="text"class="hidden" name="iduser" value="<%=request.getAttribute("iduser")%>">
						
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label>Nom</label> <input type="text" class="form-control"
									name="nom" value="<%=request.getAttribute("nom")%>">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>Prenom</label> <input type="text" class="form-control"
									name="prenom" value="<%=request.getAttribute("prenom")%>">
							</div>
						</div>


					</div>
					<div class="form-group">
						<label>Mail</label> <input type="text" class="form-control"
							name="mail" value="<%=request.getAttribute("maill")%>">

					</div>
					<div class="form-group">
						<label>Password</label> <input type="password"
							class="form-control" name="mdp" placeholder="Password">
					</div>
					<div class="form-group">
						<label>Ancien Password</label> <input type="password"
							class="form-control" name="confmdp" placeholder="Password">
					</div>
				<div class="row text-center">
				<div class="col-md-6">
					<button type="submit" class="btn  btn-success glyphicon glyphicon-pencil"></button>
				</div>
				<div class="col-md-6">
				</form>
				<form method="post" action="suppuser">
				<input type="text"class="hidden" name="mail" value="<%=request.getAttribute("maill")%>">
				<button type="submit" class="btn  btn-danger glyphicon glyphicon-trash"></button>
				</form>
				</div>
</div>
</div>
</div>


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
	
<div class="ajouttache hidden col-md-8 col-md-offset-2">
	<form method="post" action="AddRequest">
   <div class="form-group ">
    <label >Nom</label>
    <input type="text" class="hidden" name="iduser" value="<%=request.getAttribute("iduser")%>">
    <input type="text" class="hidden" name="mail" value="<%=request.getAttribute("maill")%>">
    <input type="text" class="hidden" name="from" value="add">
    
    <input type="text" class="hidden" name="type" value="tache">
    <input type="text" class="hidden" name="etat" value="soumis">
    <input type="text" class="form-control" name="nom" placeholder="nom">
    </div>
    <div class="form-group">
    <label >Description</label>
    <input type="text" class="form-control" name="description" placeholder="description">
    
    <input type="text" class="form-control hidden" name="ref" placeholder="IDprojet" value="<%=request.getAttribute("IDprojet")%>">
  </div>
  
  <div class="form-group">
  <div class="row">
  <div class="col-md-6">
    <label >Delai estimé:</label>
    <select class="form-control" name="delai">
    		
			<option label="court">Court</option>
			<option label="moyen">Moyen</option> 
			<option label="long">Long</option> 
		</select>
  </div>
  
  <div class="col-md-6">
    <label >Cout</label>
    <select class="form-control" name="cout">
    		
			<option label="court">Faible</option>
			<option label="moyen">Moyenne</option> 
			<option label="long">Elevée</option> 
		</select>
  </div>
  </div>  
  </div>
   <div class="form-group text-center" style="margin-top:10px;">
  <button type="submit" class="btn btn-default btn-success glyphicon glyphicon-plus"></button>
  </div>
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
		
		var checkcompte=0;
		
function acc(){
			if (checkcompte==0){
			$('#form_acount').removeClass("hidden ");
			
			checkcompte=1;
			}
			else{
			$('#form_acount').addClass("hidden ");
			
			checkcompte=0;
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