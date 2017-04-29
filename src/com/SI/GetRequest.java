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
		
		String usermail=req.getParameter("mail");
		//recuperation information requete
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
				Query q3 = new Query("request");
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
				String iDMOA="";
				Boolean droitajouttache=false;
				
				long identifiant= 0; 	 				
				for(Entity u:pq3.asIterable()){
					identifiant =u.getKey().getId();  
					if(identifiant==idR){
							
						
						req.setAttribute("IDprojet", identifiant);
						titreprojet=u.getProperty("nom").toString();
						descriptionprojet=u.getProperty("description").toString();
						delaiprojet=u.getProperty("delai").toString();
						coutprojet=u.getProperty("cout").toString();
						etatprojet=u.getProperty("etat").toString();
						if(!etatprojet.equals("soumis")){
						iDMOA=u.getProperty("history").toString();
						}
					
						 
		
					}
				}
				String testetatp="";
				String testetatn="";
				if(etatprojet.equals("soumis")){
					testetatn="hidden";
				}else{
					testetatp="hidden";
				}
					
				if(!etatprojet.equals("soumis")){
					Query q = new Query("request");
					q.addFilter("ref", FilterOperator.EQUAL,req.getParameter("idRequete"));
					PreparedQuery pq = dataStore.prepare(q);
					
					System.out.println("Coucou");
					for(Entity u:pq.asIterable()){
						String maildev="";
						String nomdev="";
						
						
						if(u.getProperty("etat").toString().equals("soumis")){
							nomdev="soumis";
						}else{
						Query dev = new Query("user");
						PreparedQuery devL = dataStore.prepare(dev);
						for(Entity devE:devL.asIterable()){
							String devI=""+devE.getKey().getId();  
							if(devI.equals(u.getProperty("history").toString())){
								maildev=devE.getProperty("mail").toString();
								nomdev="<A HREF='mailto:"+devE.getProperty("mail").toString()+"'>"+devE.getProperty("nom").toString()+"</A>";
										
										
							}
							}
					}
						
						tablep+="<tr>"
								+ "<td>"+u.getProperty("nom")+"</td>"
								+ "<td>"+u.getProperty("description")+"</td>"
								+ "<td>"+u.getProperty("delai")+"</td>"
								+ "<td>"+u.getProperty("cout")+"</td>"
								+ "<td>"+nomdev+"</td>"
								
								//+ "<td><form method='post' action='AcceptRequest'></td>" 
								//+ "<td><input type='text' class='hidden' name='idhistory' value=''></td>"
								//+ "<td><input type='text' class='hidden' name='mail' value=''></td>"
								//+ "<td><input type='text' class='hidden' name='idRequete' value="+u.getKey().getId()+"></td>"	
								//+ "<td><input type='submit' VALUE='Valider'/><input type='text' class='hidden' name='from' value='accept'></form><td>"
								//+ "<td><form method='post' action='GetRequest'></td>" 
								//+ "<td><input type='text' class='hidden' name='idRequete' value="+u.getKey().getId()+"></td>"	
								//+ "<td><input type='submit' VALUE='Detail'/><input type='text' class='hidden' name='from' value='get'><td>"	
								//+ "<td></form></td>"
								+ "</tr>"; 

						System.out.println("Dans GetRequestServlet, champs reçus: " + req.getParameter("idRequete") + u.getProperty("nom"));	 
			
						
					}
					
					Query moa = new Query("user");
					PreparedQuery moaL = dataStore.prepare(moa);
					for(Entity moaE:moaL.asIterable()){
						String moaI=""+moaE.getKey().getId();  
						if(moaI.equals(iDMOA)){
							droitajouttache=moaE.getProperty("mail").toString().equals(usermail);
							req.setAttribute("moalead", "<A HREF='mailto:"+moaE.getProperty("mail").toString()+"'>"+moaE.getProperty("nom").toString()+"</A>");
							req.setAttribute("iduser", iDMOA);
							req.setAttribute("maill", usermail);
							System.out.println("Dans moaE: " + usermail + iDMOA + droitajouttache);
						}
					}
				}
				
				if(droitajouttache){
					req.setAttribute("moaajout", "");
				}else{
					req.setAttribute("moaajout", " hidden");
				}
				
				
				req.setAttribute("titreprojet", titreprojet);
				req.setAttribute("descriptionprojet", descriptionprojet);
				req.setAttribute("delaiprojet", delaiprojet);
				req.setAttribute("coutprojet", coutprojet);
				req.setAttribute("testetatp", testetatp);
				req.setAttribute("testetatn", testetatn);
				req.setAttribute("tabletache", tablep);
				
				
	 
				getServletContext().getRequestDispatcher("/consultationRequete.jsp").forward(req, resp);

		}	
}
	

