package iaws.covoiturage.ws.contractfirst;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.XPathParam;

import domain.Prof;
import org.w3c.dom.Element;

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
	public Element handleInscriptionRequest(@XPathParam("/InscriptionRequest/nom") String nom,
			@XPathParam("/InscriptionRequest/prenom") String prenom,
			@XPathParam("/InscriptionRequest/mail") String mail,			
			@XPathParam("/InscriptionRequest/adresse") String adresse) throws Exception {

		//get latitude et longitude
		double latitude = 0.00;
		double longitude = 0.00;
		
				
		//Element rep = inscriptionService.rajouterProf(nom, prenom, mail, adresse, latitude, longitude);
		
		Element test = XmlHelper.getRootElementFromFileInClasspath("Inscription.xml");
		
		return test;
	}

}