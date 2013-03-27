package domain;

public class MailException extends Exception{

	public MailException(){
		System.out.print("Erreur, ce Mail existe dejà. Code Erreur 100");
	}
}
