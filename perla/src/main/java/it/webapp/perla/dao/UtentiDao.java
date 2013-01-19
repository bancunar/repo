package it.webapp.perla.dao;

import it.webapp.perla.beans.Utente;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface UtentiDao {
	public Utente getUtenteForAuthentication(String username) throws DataAccessException;
	public Utente getUtente(Utente ut) throws DataAccessException;
	public List<Utente> getListaUtenti(Map<String, Object> filtro) throws DataAccessException;
	public Utente insertUtente(Utente ut) throws DataAccessException;
	public Utente updateUtente(Utente ut) throws DataAccessException;
	public boolean deleteUtente(Utente ut) throws DataAccessException;
	public Utente insertStartAdmin() throws DataAccessException;
	public void updateUtenteCambioPassword(Utente ut) throws DataAccessException;
}
