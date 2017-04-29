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
		String typeprojet="";
		String etatprojet="";
		String iduserprojet="";
		String iDMOA="";
		Boolean droitajouttache=false;
		
		
		
		
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		
		//connaitre l'utilisateur
		String idU="";
		String nomU="";
		String prenomU="";
		String mailU=usermail;
		String typeU ="";
				Query user = new Query("user");
				user.addFilter("mail", FilterOperator.EQUAL,usermail);
				PreparedQuery userL = dataStore.prepare(user);
				for(Entity userE:userL.asIterable()){
							idU=userE.getKey().getId()+"";
							nomU=userE.getProperty("nom").toString();
							prenomU=userE.getProperty("prenom").toString();
							typeU=userE.getProperty("type").toString();
										
				}
				
				
				
		//recuperation projet
		
				Query projet = new Query("request");
				PreparedQuery projetL = dataStore.prepare(projet);
				
				
				
				long idProjet= 0; 
				
				
				for(Entity porjetE:projetL.asIterable()){
					idProjet =porjetE.getKey().getId();  
					if(idProjet==idR){
							
						
						req.setAttribute("IDprojet", idProjet);
						titreprojet=porjetE.getProperty("nom").toString();
						
						descriptionprojet=porjetE.getProperty("description").toString();
						delaiprojet=porjetE.getProperty("delai").toString();
						coutprojet=porjetE.getProperty("cout").toString();
						typeprojet=porjetE.getProperty("type").toString();
						etatprojet=porjetE.getProperty("etat").toString();
						iduserprojet=porjetE.getProperty("iduser").toString();
						if(!etatprojet.equals("soumis")){
						iDMOA=porjetE.getProperty("history").toString();
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
				
				String editproj="";
				//autorisation d'edition du projet si bon client
				if(idU.equals(iduserprojet)){
					editproj="<div id='editProj'class='row hidden'>"
							+ "<div class='col-md-6 col-md-offset-3'>"
							+ "	<form method='post' action='editProj'>"
							+ "   <div class='form-group'>"
							+ "    <label >Nom</label>"
							+ "    <input type='text' class='hidden' name='idRequete' value='"+idR+"'>"
							+ "    <input type='text' class='hidden' name='mail' value='"+mailU+"'>"
							+ "<input type='text' class='hidden' name='from' value='editProj'>"
							+ "<input type='text' class='form-control' name='nom' value='"+titreprojet+"'>"
							+ "    <label >Description</label>"
							+ "<input type='text' class='form-control' name='description' value='"+descriptionprojet+"'>"
							+ "  </div>"
							+ "  <div class='form-group'>"
							+ "    <label >Delai estimé:</label>"
							+ "    <select class='form-control' name='delai'>"
							+ "<option label='court'>Court</option>"
							+ "			<option label='moyen'>Moyen</option>"
							+ "			<option label='long'>Long</option>"
							+ "		</select>"
							+ "  </div>"
							+ "  <div class='form-group'>"
							+ "   <label >Cout</label>"
							+ "    <select class='form-control' name='cout'>"
							+ "			<option label='court'>Faible</option>"
							+ "			<option label='moyen'>Moyenne</option>"
							+ "			<option label='long'>Elevée</option>"
							+ "		</select>"
							+ "  </div>   "
							+ "  <button type='submit' class='btn btn-default btn-primary'>Sauvegarder</button>"
							+ "</form>"
							+ "<form method='post' action='suppProj'>"
							+ "<input type='text' class='hidden' name='mail' value="+mailU+">"
							+ "<input type='text' class='hidden' name='from' value='suppProj'>"
							+ "<input type='text' class='hidden' name='idRequete' value="+idR+">"
							+ "<button type='submit' class='btn btn-default btn-primary'>Supprimer</button>"
							+"</form>"
							+ "</div>"
							+ "</div>"
							+"<p class='text-center '><span onclick='edit()' class='btn'> Edit projet</span></p>";
					
					
					
					
				}else{
					
				}
				req.setAttribute("editproj", editproj);
				
				
				//Recuperation du moa en charge
				String idM="";
				String nomM="";
				String prenomM="";
				String mailM="";
			
				Query moa = new Query("user");
				PreparedQuery moaL = dataStore.prepare(moa);
				for(Entity moaE:moaL.asIterable()){
					String moaI=""+moaE.getKey().getId();  
								
					if(moaI.equals(iDMOA)){
						idM= moaI;
						nomM=moaE.getProperty("nom").toString();
						prenomM=moaE.getProperty("prenom").toString();
						mailM=moaE.getProperty("mail").toString();
						droitajouttache=moaE.getProperty("mail").toString().equals(usermail);
						req.setAttribute("moalead", "<A HREF='mailto:"+moaE.getProperty("mail").toString()+"'>"+nomM+" "+prenomM+"</A>");
						req.setAttribute("iduser", iDMOA);
						req.setAttribute("maill", usermail);						
						System.out.println("Dans moaE: " + usermail + iDMOA + droitajouttache);
					}
				}
				
				//recupération des taches
				if(!etatprojet.equals("soumis")){
					Query tache = new Query("request");
					tache.addFilter("ref", FilterOperator.EQUAL,req.getParameter("idRequete"));
					PreparedQuery tacheL = dataStore.prepare(tache);
					
					System.out.println("Coucou");
					for(Entity tacheE:tacheL.asIterable()){
						
						// recuperation des devs si assigné
						String maildev="";
						String nomdev="";
						if(tacheE.getProperty("etat").toString().equals("soumis")){
							nomdev="soumis";
						}else{
						Query dev = new Query("user");
						PreparedQuery devL = dataStore.prepare(dev);
						for(Entity devE:devL.asIterable()){
							String devI=""+devE.getKey().getId();  
							if(devI.equals(tacheE.getProperty("history").toString())){
								maildev=devE.getProperty("mail").toString();
								nomdev="<A HREF='mailto:"+devE.getProperty("mail").toString()+"'>"+devE.getProperty("nom").toString()+" "+devE.getProperty("prenom").toString()+"</A>";
										
										
								}
							}
						}
						
						tablep+="<tr>";
								
								
								
						if(idM.equals(idU)){
							tablep+="<td><form method='post' action='editreq'>"
									+"<input type='text' class='form-control' name='nom' value='"+tacheE.getProperty("nom")+"'></td>"
									+ "<td><input type='text' class='form-control' name='description' value=\""+tacheE.getProperty("description")+"\"></td>"
									+ "<td><select class='form-control' name='delai' selected='"+tacheE.getProperty("delai")+"'>"
									+ "			<option label='court'>Court</option>"
									+ "			<option label='moyen'>Moyen</option>"
									+ "			<option label='long'>Elevé</option>"
									+ "		</select></td>"
									+ "<td>"
									+ "<select class='form-control' name='cout' value='"+tacheE.getProperty("cout")+"'>"
									+ "			<option label='court'>Faible</option>"
									+ "			<option label='moyen'>Moyenne</option>"
									+ "			<option label='long'>Elevée</option>"
									+ "		</select></td>"
									+ "<td>"+nomdev+"</td>"
									+"<input type='text' class='hidden' name='idproj' value="+ tacheE.getKey().getId()+">"
									+ "<input type='text' class='hidden' name='mail' value="+mailU+">"
									+ "<input type='text' class='hidden' name='idRequete' value="+idR+">"
									+ "<td><button type='submit' class='btn btn-default btn-primary'>Edit</button>"
									+"</form></td><td>"
									+ "<form method='post' action='suppreq'>"
									+"<input type='text' class='hidden' name='idproj' value="+ tacheE.getKey().getId()+">"
									+ "<input type='text' class='hidden' name='mail' value="+mailU+">"
									+ "<input type='text' class='hidden' name='idRequete' value="+idR+">"
									+ "<button type='submit' class='btn btn-default btn-primary'>Supprimer</button>"
									+"</form></td>";
									
							req.setAttribute("moaajout", "");
						}else{
							tablep+= "<td><p class=''>"+tacheE.getProperty("nom")+"</p></td>"
									+ "<td><p class=''>"+tacheE.getProperty("description")+"</td>"
									+ "<td><p class=''>"+tacheE.getProperty("delai")+"</td>"
									+ "<td><p class=''>"+tacheE.getProperty("cout")+"</td>"
									+ "<td>"+nomdev+"</td>";
							req.setAttribute("moaajout", " hidden");
						}

						tablep+= "</tr>"; 

						System.out.println("Dans GetRequestServlet, champs reçus: " + req.getParameter("idRequete") + tacheE.getProperty("nom"));	 
			
						
					}
					
					
				
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
	

