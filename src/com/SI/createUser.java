package com.SI;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.api.server.spi.auth.common.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

@SuppressWarnings("serial")
public class createUser extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String mail = req.getParameter("mail");
		String mdp = req.getParameter("mdp");
		String confmdp = req.getParameter("confmdp");
		String type = req.getParameter("type");

		System.out.println("Dans GoogleEsieeServlet, champs reçus: " + nom);

		// if(mdp.equals(confmdp)){
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Transaction tx = dataStore.beginTransaction();
		Entity user = new Entity("user");
		user.setProperty("nom", nom);
		user.setProperty("prenom", prenom);
		user.setProperty("mail", mail);
		user.setProperty("mdp", mdp);
		user.setProperty("type", type);

		dataStore.put(user);

		tx.commit();

		// Queue queue = QueueFactory.getDefaultQueue();
		// queue.add(TaskOptions.Builder.withUrl("/tacheDeFond").method(Method.POST).param("kind",
		// "Personne").param("filterProperty", "age").param("filterValue",
		// "15"));

		Query q = new Query("user");
		q.addFilter("type", FilterOperator.EQUAL, type);
		PreparedQuery pq = dataStore.prepare(q);
		String tabuser = "<table><thead></thead><tbody>";

		for (Entity u : pq.asIterable()) {
			tabuser += "<tr><td>" + u.getProperty("nom") + "</td><td>" + u.getProperty("prenom") + "</td><td>"
					+ u.getProperty("mail") + "</td></tr>";
		}

		tabuser += "</tbody></table>";
		req.setAttribute("tabuser", tabuser);
		getServletContext().getRequestDispatcher("/coucouc.jsp").forward(req, resp);

	}

}
