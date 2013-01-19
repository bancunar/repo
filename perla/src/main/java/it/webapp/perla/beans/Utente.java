package it.webapp.perla.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class Utente extends Bean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private String nome;
	private String cognome;
	private boolean attivo;
	private List<Ruolo> ruoli;
	private Collection<GrantedAuthority> authorities;
	
	private void init(){
		attivo=true;
		ruoli=new ArrayList<Ruolo>();
		authorities=null;
	}
	public Utente(){
		init();
	}
	public List<Ruolo> getRuoli() {
		return ruoli;
	}
	public void setRuoli(List<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}
	
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		if (authorities == null){
			if (ruoli!=null && !ruoli.isEmpty()){
				for (int i=0;i<ruoli.size();i++){
					authList.add(new SimpleGrantedAuthority(ruoli.get(i).getRuolo()));
				}
			}
			authorities = authList;
		}
		return authorities;
	}
	
	public Collection<GrantedAuthority> getAuthoritiesEnabled() {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		if (authorities == null){
			if (ruoli!=null && !ruoli.isEmpty()){
				for (int i=0;i<ruoli.size();i++){
					if (ruoli.get(i).isSelezionato())
						authList.add(new SimpleGrantedAuthority(ruoli.get(i).getRuolo()));
				}
			}
			authorities = authList;
		}
		return authorities;
	}
	
	public boolean isAttivo() {
		return attivo;
	}
	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
}
