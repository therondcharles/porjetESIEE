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

public class AcceptRequest extends HttpServlet {

	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		long idR = Long.parseLong(req.getParameter("idRequete"));
		System.out.println("Dans GetRequestServlet, champs reçus: " + idR);
		
		//recuperation information requete
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
				Query q3 = new Query("request");
				//q3.addFilter("id", FilterOperator.EQUAL, idR);
				PreparedQuery pq3 = dataStore.prepare(q3);
				
				
				long identifiant= 0; 	 				
				for(Entity u:pq3.asIterable()){
					identifiant =u.getKey().getId();  
					if(identifiant==idR){
						Transaction txn = dataStore.beginTransaction(); 
						try{
						u.setProperty("history",req.getParameter("idhistory") );
						u.setProperty("etat","en charge");
						dataStore.put(u);
						txn.commit(); 
						}finally{
							if(txn.isActive()){
								txn.rollback(); 
							}
						}		
						
		
					}
				}
							
				
	 
				getServletContext().getRequestDispatcher("/connectUser").forward(req, resp);

		}	
}
	
