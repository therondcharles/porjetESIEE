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
				Query q3 = new Query("request");
				PreparedQuery pq3 = dataStore.prepare(q3);
				String tablec="";
				/*
				for(Entity u:pq3.asIterable()){
					
					System.out.println("Properties: "+u.getProperties());	
					System.out.println("Id/Name "+u.getProperty("nom"));
					
					tablec+="<tr>"
							+"<td>"+u.getProperty("key_name")+"</td>"
							+ "<td>"+u.getProperty("nom")+"</td>"
							+ "<td>"+u.getProperty("description")+"</td>"
							+ "<td>"+u.getProperty("delai")+"</td>"
							+ "<td>"+u.getProperty("cout")+"</td>"
							+ "</tr>"; 				
				}
				req.setAttribute("tableCompte", tablec); 
				getServletContext().getRequestDispatcher("/consultationCompte.jsp").forward(req, resp);
			*/
				}	
}
