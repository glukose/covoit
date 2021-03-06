package services;

import org.w3c.dom.Element;

import domain.Prof;


public interface InscriptionService {
	
	/**
     * Rajoute un nouvel utilisateur dans la base
     * @param nom nom du prof
     * @param prenom le prenom 
     * @return les évaluations correspondant aux critères fournis
     */
	public Element rajouterProf(String nom2, String prenom, String mail, String adresse,
			double latitude, double longitude);
	
	public Boolean rechercheMail(String mail);

}
