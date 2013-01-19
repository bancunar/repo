package it.webapp.perla.logic;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import it.webapp.perla.beans.Utente;

public interface UtentiLogic {
	public Utente getUtenteForAuthentication(String username) throws DataAccessException;
	public Utente getUtente(Utente ut);
	public List<Utente> getListaUtenti(Map<String, Object> filtro);
	public Utente insertUtente(Utente ut);
	public Utente updateUtente(Utente ut);
	public boolean deleteUtente(Utente ut);
	Utente insertStartAdmin() throws DataAccessException;
	public void updateUtenteCambioPassword(Utente ut) throws DataAccessException;
}
