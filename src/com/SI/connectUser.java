package com.SI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.Query.FilterOperator;

public class connectUser extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		String mail = req.getParameter("mail");
		String from = req.getParameter("from");
		String mdp = req.getParameter("mdp");

		System.out.println("Dans ConnectUserServlet, champs re�us: " + mail +  mdp +from);

		// if(mdp.equals(confmdp)){
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Transaction tx = dataStore.beginTransaction();
		tx.commit();

		// Queue queue = QueueFactory.getDefaultQueue();
		// queue.add(TaskOptions.Builder.withUrl("/tacheDeFond").method(Method.POST).param("kind",
		// "Personne").param("filterProperty", "age").param("filterValue",
		// "15"));

		//Recuperation compte client
		
				Query q = new Query("user");
				q.addFilter("mail", FilterOperator.EQUAL, mail);
				
				if(from==null){
				q.addFilter("mdp", FilterOperator.EQUAL, mdp);
				}
				PreparedQuery pq = dataStore.prepare(q);
				// String tabuser = "<table><thead></thead><tbody>";
				
				String link = "/index.html";

				String nom="";
				String prenom="";
				String type="";
				String typeR="";
				String id="";
				int i =0;
				
				for(Entity u:pq.asIterable()){
					i++;
					
					nom=u.getProperty("nom") +" " ;
					type=u.getProperty("type").toString();
					prenom=u.getProperty("prenom") +" " ;
					id= ""+u.getKey().getId();
					//tabuser+="<tr><td>"+u.getProperty("nom")+"</td><td>"+u.getProperty("prenom") +"</td><td>"+u.getProperty("mail") +"</td></tr>";
		
				}
		if (i == 1) {

			
			link ="/espace.jsp";
			System.out.println("link");
		}
		String b1="";
		String b2="";
		String b3="hidden";
		String text1="";
		String text2="";
		String typeu="";
		
		Query q2 = new Query("request");
		if(type.equals("Client")){
			text1="un projet";
			req.setAttribute("typep", "projet");
			q2.addFilter("iduser", FilterOperator.EQUAL, id);			
			b2="hidden";
			typeR="			<option selected='selected'label='Client'>Client</option>"
			+ "			<option label='MOA'>MOA</option>"
			+ "			<option label='Developpeur'>Developpeur</option>";
		}
		
		if(type.equals("Developpeur")){
			b1="hidden ";
			text2="Taches";
			typeu="tache";
			q2.addFilter("history", FilterOperator.EQUAL, id);
			typeR="			<option label='Client'>Client</option>"
					+ "			<option label='MOA'>MOA</option>"
					+ "			<option selected='selected' label='Developpeur'>Developpeur</option>";
		}
		if(type.equals("MOA")){
			req.setAttribute("typep", "tache");
			text1="une tache";
			text2=" Projets";
			b3="";
			b1="hidden ";
			typeu="projet";
			q2.addFilter("history", FilterOperator.EQUAL, id);
			typeR="			<option label='Client'>Client</option>"
					+ "			<option selected='selected' label='MOA'>MOA</option>"
					+ "			<option  label='Developpeur'>Developpeur</option>";
		}
		
		
		
		//Recuperation projets utilisateur
		

		PreparedQuery pqq = dataStore.prepare(q2);
int j =0,k=0;
		String proj="<div class='row'>";
		for(Entity u:pqq.asIterable()){
			j++;
			proj+=" <div class='col-sm-6 col-md-4'><div class='thumbnail'>";
			proj+="<img src='...' alt='...'>";
			proj+="<div class='caption'>";
			proj+=" <h3>"+u.getProperty("nom")+"</h3>";
			proj+=" <p>"+u.getProperty("description")+"</p>"; 
			//proj+="<p class='text-center'><a href="+ u.getKey().getId()+" class='btn btn-primary' role='button'>Voir</a> <a href="+ u.getKey().getId()+" class='btn btn-default' role='button'>Terminer</a></p>";
			proj+="<td><form method='post' action='GetRequest'></td>"; 
			if(type.equals("Developpeur")){
				proj+= "<td><input type='text' class='hidden' name='idRequete' value="+u.getProperty("ref")+"></td>"; 
			}else{
			proj+= "<td><input type='text' class='hidden' name='idRequete' value="+u.getKey().getId()+"></td>"; 
			}proj+= "<td><input type='text' class='hidden' name='mail' value="+mail+"></td>"; 
			proj+= "<td><input type='submit' class='btn btn-primary' VALUE='Voir'/></form><td>"; 

			proj+="</div> </div> </div> ";
			if(j==3){
				j=0;
				k++;
				
				if(k>2){
					proj+="</div><div id='knowmore'class='row knowmore  hidden '>";
				}else{
					
						proj+="</div><div class='row'>";
					
				}
				
			}
		}
		
		
		proj+="</div><p class='text-center'><span onclick=\"ecoute()\" class='btn'> en savoir plus</span></p>";
		proj+="</div>";
		
	
		//recuperation projet disponibles	
		Query q3 = new Query("request");
		q3.addFilter("etat", FilterOperator.EQUAL, "soumis");
		q3.addFilter("type", FilterOperator.EQUAL, typeu);
		PreparedQuery pq3 = dataStore.prepare(q3);
			
		
		String tablep="";
		
		for(Entity u:pq3.asIterable()){
			
			
			String idrr="";
			if(type.equals("Developpeur")){
				 idrr=u.getProperty("ref").toString(); 
			}else{
				 idrr=u.getKey().getId()+""; 
			}
			
			tablep+="<tr>"
					+ "<td>"+u.getProperty("nom")+"</td>"
					+ "<td>"+u.getProperty("description")+"</td>"
					+ "<td>"+u.getProperty("delai")+"</td>"
					+ "<td>"+u.getProperty("cout")+"</td>"
					+ "<form method='post' action='AcceptRequest'>" 
					+ "<input type='text' class='hidden' name='idhistory' value="+id+">"
					+ "<input type='text' class='hidden' name='mail' value="+mail+">"
					+ "<input type='text' class='hidden' name='idRequete' value="+u.getKey().getId()+">"	
					+ "<td><button type='submit' class='btn btn-success glyphicon glyphicon-ok'></button></td>"
					+ "<input type='text' class='hidden' name='from' value='accept'></form>"
					+ "<form method='post' action='GetRequest'>" 
					+ "<input type='text' class='hidden' name='mail' value="+mail+">"
					+ "<input type='text' class='hidden' name='idRequete' value="+idrr+">"	
					+ "<td><button type='submit' class='btn btn-info glyphicon glyphicon-list-alt'></button></td>"
					+ "<input type='text' class='hidden' name='from' value='get'>"	
					+ "</form>"
					+ "</tr>"; 
					
		}
		
		
		
		//Acc�s infos compte 
		String tabC=""; 	 
		tabC+="<tr>"		
		+ "<td><form method='post' action='GetAccountInfo'></td>" 
		+ "<td><input type='text' class='hidden' name='idUser' value="+id+"></td>"	
		+ "<td><input type='submit' VALUE='DetailCompte'/><td>"	
		+ "<td></form></td>"; 		
		
		
		
		
				
		req.setAttribute("nom", nom);
		req.setAttribute("prenom", prenom);
		req.setAttribute("typeR", typeR);
		req.setAttribute("maill", mail);
		req.setAttribute("proj", proj);
		req.setAttribute("block1", b1);
		req.setAttribute("block2", b2);
		req.setAttribute("block3", b3);
		req.setAttribute("text1", text1);
		req.setAttribute("text2", text2);
		req.setAttribute("iduser", id);
		req.setAttribute("tabletache", tablep);
		
		getServletContext().getRequestDispatcher(link).forward(req, resp);

	}
}