package com.SI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.services.datastore.v1.model.Key;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

@SuppressWarnings("serial")
public class GetAccountInformation  extends HttpServlet{
	
	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String idUser = req.getParameter("idUser");
		
		
		System.out.println("Dans GetAccountInformation, champs reçus: " + idUser);
		
		//EN COURS 
		
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
				Query q3 = new Query("user");
				PreparedQuery pq3 = dataStore.prepare(q3);
				String tablec="";
				long idR = Long.parseLong(req.getParameter("idUser"));
				long identifiant = 0; 
				String tabM=""; 
				for(Entity u:pq3.asIterable()){
					identifiant =u.getKey().getId();  
					
					if(identifiant==idR){

						tablec+="<tr>"							
							+ "<td> <label> Identifiant </label> <&nbsp;</td>"
							+"<td>"+identifiant+" </br> </td>"	
							+ "<td> <label> Mail </label> &nbsp; </td>"
							+ "<td>"+u.getProperty("mail")+"</br></td>"
							+ "<td> <label> mdp </label> <&nbsp;</td>"
							+"<td>"+u.getProperty("mdp")+" </br> </td>"
							+ "<td> <label> Nom </label> &nbsp; </td>"
							+ "<td>"+u.getProperty("nom")+"</br></td>"
							+ "<td> <label> Prenom </label> &nbsp;</td>"
							+ "<td>"+u.getProperty("prenom")+"</br></td>"
							+ "<td> <label> Role </label> &nbsp;</td>"
							+ "<td>"+u.getProperty("type")+"</br></td>"
							+ "</tr>"; 
						
						tabM+="<tr>"	
								+ "<td> <div class='row'><div class='text-center container'></td>"
								+ "<td> <div class='col-md-6 col-md-offset-3'></td>"
								+ "<td> <label> Identifiant </label> <&nbsp;</td>"
								+"<td>"+identifiant+" </br> </td>"				
							
								+ "<td><form method='post' action='ModificationCompte'></td>" 
								+"<td><input type='text' class='hidden' name='idUser' value="+identifiant+"></td>" 
								+ "<td><input type='text' name='mail' value="+u.getProperty("mail")+"></br> </td>"
								+ "<td> <label> mdp </label> <&nbsp;</td>"
								+ "<td> <label> Mail </label> &nbsp; </td>"
								+ "<td><input type='text'  name='mail' value="+u.getProperty("mail")+"></br> </td>"
								+ "<td> <label> mdp </label> <&nbsp;</td>"
								+ "<td><input type='text'  name='mdp' value="+u.getProperty("mdp")+"></br> </td>"
								+ "<td> <label> Nom </label> &nbsp; </td>"
								+ "<td><input type='text'  name='nom' value="+u.getProperty("nom")+"></br> </td>"
								+ "<td> <label> Prenom </label> &nbsp;</td>"
								+ "<td><input type='text'  name='prenom' value="+u.getProperty("prenom")+"></br> </td>"
								+ "<td> <label> Role </label> &nbsp;</td>"
								+ "<td><input type='text'  name='role' value="+u.getProperty("type")+"></br> </td>"
								+ "<td><input type='submit' VALUE='ModificationCompte'/><td>"	
								+ "<td></form></td>"			
								+ "<td></div> </td>"
								+ "<td>	</div></td>"; 
					}			
				}
				
				req.setAttribute("tabM", tabM); 
				req.setAttribute("tableCompte", tablec); 
				getServletContext().getRequestDispatcher("/consultationCompte.jsp").forward(req, resp);
			
				}	
}
