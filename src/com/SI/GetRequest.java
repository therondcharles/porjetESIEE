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

public class GetRequest extends HttpServlet {

	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String idR = req.getParameter("idRequete");
		System.out.println("Dans GetRequestServlet, champs reçus: " + idR);
		
		//recuperation information requete
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
				Query q3 = new Query("request");
				com.google.appengine.api.datastore.Key clePersonnage = KeyFactory.createKey("request",idR);
				try {
					Entity personnageTrouve = dataStore.get(clePersonnage);
					System.out.println("fonctionne récupération cléPersonnage");
				} catch (EntityNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				q3.addFilter("id", FilterOperator.EQUAL, idR);
				PreparedQuery pq3 = dataStore.prepare(q3);
				String tablep="";
							 				
				for(Entity u:pq3.asIterable()){
					long identifiant =u.getKey().getId();  
					System.out.println("Id/Name "+u.getKey().getId());
					
					tablep+="<tr>"
							+"<td>"+u.getProperty("key_name")+"</td>"
							+ "<td>"+u.getProperty("nom")+"</td>"
							+ "<td>"+u.getProperty("description")+"</td>"
							+ "<td>"+u.getProperty("delai")+"</td>"
							+ "<td>"+u.getProperty("cout")+"</td>"
							+ "</tr>"; 
							
				}
				req.setAttribute("tabletache", tablep);
				System.out.println("Affichage tableau tache: "+req.getAttribute("tabletache")); 
				getServletContext().getRequestDispatcher("/consultationRequete.jsp").forward(req, resp);

		}	
}
	

