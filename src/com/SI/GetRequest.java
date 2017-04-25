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
import com.google.appengine.api.datastore.Text;

public class GetRequest extends HttpServlet {

	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		long idR = Long.parseLong(req.getParameter("idRequete"));
		System.out.println("Dans GetRequestServlet, champs reçus: " + idR);
		
		//recuperation information requete
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
				Query q3 = new Query("request");
				//q3.addFilter("id", FilterOperator.EQUAL, idR);
				PreparedQuery pq3 = dataStore.prepare(q3);
				
				String nom=""; 
				String type=""; 
				String description=""; 
				String etat=""; 
				String delai=""; 
				String cout=""; 
				String tablep="";
				String tabM=""; 
				long identifiant= 0; 	 				
				for(Entity u:pq3.asIterable()){
					identifiant =u.getKey().getId();  
					if(identifiant==idR){
									
						tablep+="<tr>"							
							+ "<td> <label> Identifiant </label> <&nbsp;</td>"
							+"<td>"+identifiant+" </br> </td>"	
							+ "<td> <label> Nom </label> &nbsp; </td>"
							+ "<td>"+u.getProperty("nom")+"</br></td>"
							+ "<td> <label> Type </label> <&nbsp;</td>"
							+"<td>"+u.getProperty("type")+" </br> </td>"
							+ "<td> <label> Description </label> &nbsp; </td>"
							+ "<td>"+u.getProperty("description")+"</br></td>"
							+ "<td> <label> Etat </label> &nbsp;</td>"
							+ "<td>"+u.getProperty("etat")+"</br></td>"
							+ "<td> <label> Delai </label> &nbsp;</td>"
							+ "<td>"+u.getProperty("delai")+"</br></td>"
							+ "<td> <label> Cout </label> &nbsp;</td>"
							+ "<td>"+u.getProperty("cout")+"</td>"
							+ "</tr>";  
		
					}
				}
							
					
				req.setAttribute("tableP", tablep);
	 
				getServletContext().getRequestDispatcher("/consultationRequete.jsp").forward(req, resp);

		}	
}
	

