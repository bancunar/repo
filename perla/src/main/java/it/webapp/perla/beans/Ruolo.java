package it.webapp.perla.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ruolo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String ROLE_ADMIN="ROLE_ADMIN";
	public static String ROLE_CONTROLLER="ROLE_CONTROLLER";
	public static String ROLE_USER="ROLE_USER";
	public static String ROLE_GUEST="ROLE_GUEST";
	
	private String ruolo;
	private String maschera;
	private String descrizione;
	private boolean selezionato;
	
	public static List<String> getRoles(){
		List<String> l=new ArrayList<String>();
		l.add(ROLE_ADMIN);
		l.add(ROLE_CONTROLLER);
		l.add(ROLE_USER);
		l.add(ROLE_GUEST);
		return l;
	}
	public static List<Ruolo> getListaRuoli(){
		List<Ruolo> l=new ArrayList<Ruolo>();
		l.add(new Ruolo(ROLE_ADMIN,"",""));
		l.add(new Ruolo(ROLE_CONTROLLER,"",""));
		l.add(new Ruolo(ROLE_USER,"",""));
		l.add(new Ruolo(ROLE_GUEST,"",""));
		return l;
	}
	public Ruolo() {
		super();
	}
	public Ruolo(String ruolo, String maschera, String descrizione) {
		super();
		this.ruolo = ruolo;
		this.maschera = maschera;
		this.descrizione = descrizione;
		this.selezionato=false;
	}

	
	public boolean isSelezionato() {
		return selezionato;
	}
	public void setSelezionato(boolean selezionato) {
		this.selezionato = selezionato;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public String getMaschera() {
		return maschera;
	}
	public void setMaschera(String maschera) {
		this.maschera = maschera;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
