package iaws.covoiturage.ws.contractfirst;

import javax.swing.text.Element;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.XPathParam;

import domain.Prof;

import services.InscriptionService;


@Endpoint
public class InscriptionEndpoint {
private InscriptionService inscriptionService;
	
	private static final String NAMESPACE_URI = "http://iaws/ws/contractfirst/exemple";
	
	public InscriptionEndpoint(InscriptionService inscriptionService) {
		this.inscriptionService = inscriptionService;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "InscriptionXMLRequest")
	@ResponsePayload
	public Element handleInscriptionRequest(@XPathParam("/InscriptionRequest/Prof/nom") String nom,
			@XPathParam("/InscriptionRequest/Prof/prenom") String prenom,
			@XPathParam("/InscriptionRequest/Prof/mail") String mail,			
			@XPathParam("/InscriptionRequest/Prof/adresse") String adresse) throws Exception {

		//get latitude et longitude
		double latitude = 0.00;
		double longitude = 0.00;
		
		//renvoi message erreur si email déjà utilisé
		
		//renvoi message erreur si email pas conforme
		
		//renvoi message erreur si adresse postale inconnue
		
		Prof p = new Prof(nom, prenom, mail, adresse, latitude, longitude);
				
		Element resp = inscriptionService.rajouterProf(p);
		
		return resp;
	}

}