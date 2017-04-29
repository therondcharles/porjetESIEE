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

public class suppreq extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String id= req.getParameter("idproj");
	
		
		
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Query q3 = new Query("request");
		
		PreparedQuery pq3 = dataStore.prepare(q3);	
		
		for(Entity request:pq3.asIterable()){
			if(id.equals(request.getKey().getId()+"")){
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
		}
	//Redirection vers connectUser avec propriétés mail et mdp
		getServletContext().getRequestDispatcher("/GetRequest").forward(req, resp);

	}
}
