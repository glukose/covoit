package iaws.covoiturage.ws.contractfirst;

import javax.swing.text.Element;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.XPathParam;

import domain.Identite;

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
	public Element handleInscriptionRequest(@XPathParam("/InscriptionXMLRequest/Identite/Nom") String nom,
			@XPathParam("/InscriptionXMLRequest/Identite/Prenom") String prenom
			) throws Exception {
		
		System.out.println("-- nom du prof : " + nom);
		System.out.println("-- prenom prof : " + prenom);		
		
		Identite prof = new Identite(nom, prenom);
				
		Element resp = inscriptionService.rajouterProf(prof);
		
		return resp;
	}

}