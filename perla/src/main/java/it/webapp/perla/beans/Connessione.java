package it.webapp.perla.beans;

import it.webapp.perla.beans.setStatic.TipoConnessione;

import java.io.Serializable;

public class Connessione extends Bean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String driver;
	private String url;
	private String username;
	private String password;
	private String tipo;
	private String dbParam;
	
	public Connessione() {
		init();
	}

	private void init() {
		tipo=TipoConnessione.MONDRIAN.toString();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDbParam() {
		return dbParam;
	}

	public void setDbParam(String dbParam) {
		this.dbParam = dbParam;
	}
}
