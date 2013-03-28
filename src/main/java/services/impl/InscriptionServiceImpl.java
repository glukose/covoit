package services.impl;
import services.*;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import iaws.covoiturage.ws.contractfirst.XmlHelper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;

import services.InscriptionService;


public class InscriptionServiceImpl implements InscriptionService{

	/**
     * Rajoute un nouvel utilisateur dans la base
     * @param nom nom du prof
     * @param prenom le prenom 
     * @return les évaluations correspondant aux critères fournis 
     */
	public Element rajouterProf(String nom, String prenom, String mail, String adresse,
			double latitude, double longitude){
		
		Element elem = null;
		Boolean mailValide = true;
		try
		{
			//renvoi message erreur si email déjà utilisé  CODE 100
			if (rechercheMail(mail) == true){
				mailValide=false;
				try {
					elem = XmlHelper.getRootElementFromFileInClasspath("ErreurCode100.xml");
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//System.out.println(rechercheMail(mail));
		
			//renvoi message erreur si email pas conforme  CODE 110 
			if (confirmMail(mail) == false){
				
				mailValide=false;
				try {
					elem = XmlHelper.getRootElementFromFileInClasspath("ErreurCode110.xml");
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//System.out.println(confirmMail(mail));
			
			//renvoi message erreur si adresse postale inconnue  CODE erreur 200
			//ça je m'en occupe		
		
		
			//rajouter le prof dans le fichier
			if (mailValide)
			{
				
			
				//on va chercher le chemin et le nom du fichier
		        String adressedufichier = System.getProperty("user.dir") + "/src/main/java/domain" + "/fichier.txt";
		        System.out.print(adressedufichier);
		
		        //on met try si jamais il y a une exception
		       
	            /**
	                * BufferedWriter a besoin d un FileWriter, 
	                * les 2 vont ensemble, on donne comme argument le nom du fichier
	                * true signifie qu on ajoute dans le fichier (append), on ne marque pas par dessus 
	
	                */
	            FileWriter fw = new FileWriter(adressedufichier, true);
	
	            // le BufferedWriter output auquel on donne comme argument le FileWriter fw cree juste au dessus
	            BufferedWriter output = new BufferedWriter(fw);
	
	            //Ecriture de la balise d'ouverture <prof>
	            output.write("<prof>");
	            
	            //Ecriture du nom
	            output.write("<nom>");
	            output.write(nom);
	            output.write("</nom>");
	            
	            //Ecriture du prenom
	            output.write("<prenom>");
	            output.write(prenom);
	            output.write("</prenom>");
	            
	            //Ecriture de l'email
	            output.write("<mail>");
	            output.write(mail);
	            output.write("</mail>");
	            
	            //Ecriture de l'adresse
	            output.write("<adresse>");
	            output.write(adresse);
	            output.write("</adresse>");
	            
	            //Ecriture de la Latitude
	            output.write("<latitude>");
	            output.write(String.valueOf(latitude));
	            output.write("</latitude>");
	            
	            //Ecriture de la Longitude
	            output.write("<longitude>");
	            output.write(String.valueOf(longitude));
	            output.write("</longitude>");
	            
	            //Ecriture de la balise fermeture </prof>
	            output.write("</prof>");
	
	            //On revient à la ligne.
	            output.write("\n");
	            
	
	            output.flush();
	            //ensuite flush envoie dans le fichier, ne pas oublier cette methode pour le BufferedWriter
	
	            output.close();
	            //et on le ferme
	            System.out.println("fichier créé ou modifier");
		        
			}
		}
		
        catch(IOException ioe){
                System.out.print("Erreur : ");
                ioe.printStackTrace();
                }
		
		//retourner réponse XML de bonne inscription
		try {
			elem = XmlHelper.getRootElementFromFileInClasspath("Inscription.xml");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return elem;
	}
	
	public Boolean rechercheMail(String mail){
		
		//String fichier ="fichier.txt";
		Boolean trouve=false;
		int index_debut = 0;
		int index_fin = 0 ;
		
		String adressedufichier = System.getProperty("user.dir") + "/src/main/java/domain" + "/fichier.txt";
        System.out.print(adressedufichier);
		
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(adressedufichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			
			while ((ligne=br.readLine())!=null && trouve == false){

				//IL faut recuperer les mails.
				//On cherche les positions des balises ouvrante et fermante.
				//Puis on recupere dans un string ce qu'il y a entre ces deux balises
				index_debut=ligne.indexOf("<mail>", 0) + 6;
				index_fin=ligne.indexOf("</mail>", index_debut);
				String fichierMail = ligne.substring(index_debut, index_fin);
				System.out.println(ligne);
				System.out.println(fichierMail);
				System.out.println(index_debut);
				System.out.println(index_fin);
				
				//On compare les mails
				if(mail.equals(fichierMail)==true) trouve = true;

			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		return trouve;
	}
	
	
	//Fonction qui regarde si le mail est conforme
	public Boolean confirmMail(String mail){
		
		Boolean trouve = true;
		
		//On cherche la position du symbole "@", si il n'y en a pas
		//alors ça retourne -1.
		if(mail.indexOf("@univ-tlse3.fr")==-1){
			trouve = false;
		}

		return trouve;
	}
	
	
	
}
