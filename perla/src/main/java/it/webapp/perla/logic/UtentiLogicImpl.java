package it.webapp.perla.logic;

import it.webapp.perla.beans.Utente;
import it.webapp.perla.dao.UtentiDao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public class UtentiLogicImpl implements UtentiLogic {
	private UtentiDao utentiDao;
	
	
	public void setUtentiDao(UtentiDao utentiDao) {
		this.utentiDao = utentiDao;
	}

	@Override
	public Utente getUtenteForAuthentication(String username) {
		return this.utentiDao.getUtenteForAuthentication(username);
	}
	
	@Override
	public Utente getUtente(Utente ut) {
		return this.utentiDao.getUtente(ut);
	}

	@Override
	public List<Utente> getListaUtenti(Map<String, Object> filtro) {
		return this.utentiDao.getListaUtenti(filtro);
	}
	
	@Override
	public Utente insertStartAdmin() throws DataAccessException {
		return this.utentiDao.insertStartAdmin();
	}

	@Override
	public Utente insertUtente(Utente ut) {
		return this.utentiDao.insertUtente(ut);
	}

	@Override
	public Utente updateUtente(Utente ut) {
		return this.utentiDao.updateUtente(ut);
	}

	@Override
	public boolean deleteUtente(Utente ut) {
		this.utentiDao.deleteUtente(ut);
		return false;
	}

	@Override
	public void updateUtenteCambioPassword(Utente ut) throws DataAccessException {
		this.utentiDao.updateUtenteCambioPassword(ut);
	}

}
