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
		String mdp = req.getParameter("mdp");

		System.out.println("Dans GoogleEsieeServlet, champs reçus: " + mail + mdp);

		// if(mdp.equals(confmdp)){
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Transaction tx = dataStore.beginTransaction();
		tx.commit();

		// Queue queue = QueueFactory.getDefaultQueue();
		// queue.add(TaskOptions.Builder.withUrl("/tacheDeFond").method(Method.POST).param("kind",
		// "Personne").param("filterProperty", "age").param("filterValue",
		// "15"));

		Query q = new Query("user");
		q.addFilter("mail", FilterOperator.EQUAL, mail);
		q.addFilter("mdp", FilterOperator.EQUAL, mdp);
		PreparedQuery pq = dataStore.prepare(q);
		// String tabuser = "<table><thead></thead><tbody>";
		String tabuser = "/index.html";
<<<<<<< HEAD
		String nom="";
		String type="";
		int i =0;
		
		for(Entity u:pq.asIterable()){
			i++;
			nom=u.getProperty("nom") +" " +  u.getProperty("prenom") +" " +  u.getKey();
			type=""+u.getProperty("type");
			//tabuser+="<tr><td>"+u.getProperty("nom")+"</td><td>"+u.getProperty("prenom") +"</td><td>"+u.getProperty("mail") +"</td></tr>";
=======
		String nom = "";
		String type = "";
		int i = 0;

		for (Entity u : pq.asIterable()) {
			i++;
			nom = u.getProperty("nom") + " " + u.getProperty("prenom") + " " + u.getKey();
			type = "" + u.getProperty("type");
			// tabuser+="<tr><td>"+u.getProperty("nom")+"</td><td>"+u.getProperty("prenom")
			// +"</td><td>"+u.getProperty("mail") +"</td></tr>";
>>>>>>> origin/master
		}
		if (i == 1) {
			tabuser = "/espace.jsp";
			
		}

		// tabuser+="</tbody></table>";
		req.setAttribute("tabuser", tabuser);
		req.setAttribute("nom", nom);
		// req.setAttribute(" ",tabuser );
		getServletContext().getRequestDispatcher(tabuser).forward(req, resp);

	}

}
