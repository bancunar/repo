package it.webapp.perla.beans;

public class UtenteSession {
	private Utente utente;
	private String IdSessione;
	
	public UtenteSession(){
		super();
	}
	public UtenteSession(Utente utenteForAuthentication) {
		this.utente=utenteForAuthentication;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public String getIdSessione() {
		return IdSessione;
	}
	public void setIdSessione(String idSessione) {
		IdSessione = idSessione;
	}
}
