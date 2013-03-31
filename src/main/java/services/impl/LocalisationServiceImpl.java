package services.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import services.LocalisationService;

import domain.Prof;

public class LocalisationServiceImpl implements LocalisationService {

	
	public Vector<Prof> localiserVoisin(Double latitude, Double longitude, Integer perimetre){
		
		Vector<Prof> listeProf = new Vector<Prof>();
		Vector<Prof> listeMembre = new Vector<Prof>();
		Integer index_debut = 0;
		Integer index_fin = 0;
		Integer i = 0;
		String nom;
		String prenom;
		String mail;
		String adresse;
		String lat;
		String longi;
		Boolean trouve = false;
		
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream("fichier.txt"); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				System.out.println(ligne);
				
				index_debut=ligne.indexOf("<nom>", 0) + 5;
				index_fin=ligne.indexOf("</nom>", index_debut);
				nom = ligne.substring(index_debut, index_fin);
				
				index_debut=ligne.indexOf("<prenom>", 0) + 8;
				index_fin=ligne.indexOf("</prenom>", index_debut);
				prenom = ligne.substring(index_debut, index_fin);
				
				index_debut=ligne.indexOf("<mail>", 0) + 6;
				index_fin=ligne.indexOf("</mail>", index_debut);
				mail = ligne.substring(index_debut, index_fin);
				
				index_debut=ligne.indexOf("<adresse>", 0) + 9;
				index_fin=ligne.indexOf("</adresse>", index_debut);
				adresse = ligne.substring(index_debut, index_fin);
				
				index_debut=ligne.indexOf("<latitude>", 0) + 10;
				index_fin=ligne.indexOf("</latitude>", index_debut);
				lat = ligne.substring(index_debut, index_fin);
				
				index_debut=ligne.indexOf("<longitude>", 0) + 11;
				index_fin=ligne.indexOf("</longitude>", index_debut);
				longi = ligne.substring(index_debut, index_fin);
				
				Prof prof;
				listeMembre.addElement(prof = new Prof(nom, prenom, mail, adresse, Double.parseDouble(lat), Double.parseDouble(longi)));

				
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		while (i<listeMembre.size() || trouve == false)
		{
			if(perimetre <= distFrom(latitude, longitude, listeMembre.elementAt(i).getLatitude(), listeMembre.elementAt(i).getLongitude())){
				
				listeProf.add(listeMembre.elementAt(i));
			}
		}
		
		
		return listeProf;
		
	}
	
	public double distFrom(double lat1, double lng1, double lat2, double lng2) {
		
	    double earthRadius = 3958.75;
	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);
	    double sindLat = Math.sin(dLat / 2);
	    double sindLng = Math.sin(dLng / 2);
	    double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(lat1) * Math.cos(lat2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double dist = earthRadius * c;

	    return dist;
	}

}
