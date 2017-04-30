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
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		String iduser = req.getParameter("iduser");
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String mail = req.getParameter("mail");
		String mdp = req.getParameter("mdp");
		String confmdp = req.getParameter("confmdp");
		
		String mailcheck = "";
		
		
		Query q2= new Query("user");
		q2.addFilter("mail", FilterOperator.EQUAL, mail);
		PreparedQuery pq2 = dataStore.prepare(q2);	

		for (Entity u2 : pq2.asIterable()) {
			mailcheck =u2.getKey().getId()+"";
		}
		
		if(mailcheck.equals(iduser)||mailcheck.equals("")){
		Query q = new Query("user");
		q.addFilter("mdp", FilterOperator.EQUAL, confmdp);
		PreparedQuery pq = dataStore.prepare(q);	

		for (Entity u : pq.asIterable()) {
			String idE=u.getKey().getId()+"";
			if(idE.equals(iduser)){
				Transaction tx = dataStore.beginTransaction();
				Entity user = new Entity("user");
				u.setProperty("nom", nom);
				u.setProperty("prenom", prenom);
				u.setProperty("mail", mail);
				u.setProperty("mdp", mdp);

				dataStore.put(u);

				tx.commit();
				
			}
		
		
		
		}

		getServletContext().getRequestDispatcher("/connectUser").forward(req, resp);
		}
		else{

			getServletContext().getRequestDispatcher("/").forward(req, resp);
		}
		

		System.out.println("Dans GoogleEsieeServlet, champs reçus: " + nom);

		

		// Queue queue = QueueFactory.getDefaultQueue();
		// queue.add(TaskOptions.Builder.withUrl("/tacheDeFond").method(Method.POST).param("kind",
		// "Personne").param("filterProperty", "age").param("filterValue",
		// "15"));

		

		 
	//Redirection vers connectUser avec propriétés mail et mdp

	}
}
