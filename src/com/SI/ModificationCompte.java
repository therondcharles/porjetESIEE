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

public class ModificationCompte extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String idUser = req.getParameter("idUser");
		String mail = req.getParameter("mail"); 
		String mdp = req.getParameter("mdp"); 
		String nom = req.getParameter("nom"); 
		String prenom = req.getParameter("prenom"); 
		String role = req.getParameter("role"); 
		
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Query q3 = new Query("user");
		q3.addFilter("mail", FilterOperator.EQUAL, mail);
		q3.addFilter("mdp", FilterOperator.EQUAL, mdp);
		PreparedQuery pq3 = dataStore.prepare(q3);	
		
		for(Entity u:pq3.asIterable()){
			Transaction txn = dataStore.beginTransaction(); 
			try{
			u.setProperty("mail", mail);
			u.setProperty("mdp", mdp);
			u.setProperty("nom", nom);
			u.setProperty("prenom", prenom);
			u.setProperty("role", role);
			dataStore.put(u);
			txn.commit(); 
			}finally{
				if(txn.isActive()){
					txn.rollback(); 
				}
			}
		}
	//Redirection vers connectUser avec propriétés mail et mdp
		//getServletContext().getRequestDispatcher("/espace.jsp").forward(req, resp);

	}
}
