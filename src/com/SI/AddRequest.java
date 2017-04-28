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
public class AddRequest extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String nom = req.getParameter("nom");
		String mail = req.getParameter("mail");
		String iduser = req.getParameter("iduser");
		String description = req.getParameter("description");
		String delai = req.getParameter("delai");
		String cout = req.getParameter("cout");
		String etat = req.getParameter("etat");
		String typep = req.getParameter("type");
		String ref =req.getParameter("ref");
		
	
		if(typep.equals("projet") || (typep.equals("tache")&&!ref.equals("")) ){
		//if(mdp.equals(confmdp)){
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Transaction  tx = dataStore.beginTransaction();
		Entity request = new Entity("request");
		request.setProperty("nom", nom);
		request.setProperty("type", typep);
		request.setProperty("iduser", iduser);
		request.setProperty("description", description);
		request.setProperty("delai", delai);
		request.setProperty("cout", cout);
		request.setProperty("etat", etat);
		request.setProperty("history", null);		
		request.setProperty("ref", ref);
		dataStore.put(request);
		tx.commit();
		}
		
		//Queue queue = QueueFactory.getDefaultQueue();
		//queue.add(TaskOptions.Builder.withUrl("/tacheDeFond").method(Method.POST).param("kind", "Personne").param("filterProperty", "age").param("filterValue", "15"));
		
		
		
			
	String link ="/connectUser";
	

		getServletContext().getRequestDispatcher(link).forward(req, resp);
	
	}

}
