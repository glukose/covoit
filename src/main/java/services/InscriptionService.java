package services;

import javax.swing.text.Element;

import domain.Identite;


public interface InscriptionService {
	
	/**
     * Rajoute un nouvel utilisateur dans la base
     * @param nom nom du prof
     * @param prenom le prenom 
     * @return les évaluations correspondant aux critères fournis
     */
	public Element rajouterProf(Identite prof);

}
