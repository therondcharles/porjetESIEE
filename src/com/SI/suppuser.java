package com.SI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.Transaction;
import com.google.api.services.datastore.v1.model.Key;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

public class suppuser extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String mail = req.getParameter("mail");
	
		
		
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		
		
Query q2 = new Query("user");
		q2.addFilter("mail", FilterOperator.EQUAL,mail);
		PreparedQuery pq2 = dataStore.prepare(q2);	
		
		for(Entity request:pq2.asIterable()){
			Transaction txn = dataStore.beginTransaction(); 
			try{
				dataStore.delete(request.getKey());
			txn.commit(); 
			}finally{
				if(txn.isActive()){
					txn.rollback(); 
				}
			}
		
		}
		
		
		
	//Redirection vers connectUser avec propriétés mail et mdp
		getServletContext().getRequestDispatcher("/").forward(req, resp);

	}
}
