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
import com.google.appengine.api.datastore.Text;

public class GetRequest extends HttpServlet {

	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		long idR = Long.parseLong(req.getParameter("idRequete"));
		System.out.println("Dans GetRequestServlet, champs reçus: " + idR);
		
		//recuperation information requete
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
				Query q3 = new Query("request");
				//q3.addFilter("id", FilterOperator.EQUAL, idR);
				PreparedQuery pq3 = dataStore.prepare(q3);
				
				String nom=""; 
				String type=""; 
				String description=""; 
				String etat=""; 
				String delai=""; 
				String cout=""; 
				String tablep="";
				String tabM=""; 
				String titreprojet="";
				String descriptionprojet="";
				String delaiprojet="";
				String coutprojet="";
				String etatprojet="";
				
				long identifiant= 0; 	 				
				for(Entity u:pq3.asIterable()){
					identifiant =u.getKey().getId();  
					if(identifiant==idR){
									
						
						titreprojet=u.getProperty("nom").toString();
						descriptionprojet=u.getProperty("description").toString();
						delaiprojet=u.getProperty("delai").toString();
						coutprojet=u.getProperty("cout").toString();
						etatprojet=u.getProperty("etat").toString();
						 
		
					}
				}
				String testetatp="";
				String testetatn="";
				if(etatprojet.equals("soumis")){
					testetatn="hidden";
				}else{
					testetatp="hidden";
				}
					
				req.setAttribute("titreprojet", titreprojet);
				req.setAttribute("descriptionprojet", descriptionprojet);
				req.setAttribute("delaiprojet", delaiprojet);
				req.setAttribute("coutprojet", coutprojet);
				req.setAttribute("testetatp", testetatp);
				req.setAttribute("testetatn", testetatn);
	 
				getServletContext().getRequestDispatcher("/consultationRequete.jsp").forward(req, resp);

		}	
}
	

