package it.webapp.perla.dao;

import it.webapp.perla.beans.Connessione;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface ReportDao {

	public Connessione getConnessione(Connessione con) throws DataAccessException;
	public List<Connessione> getListaConnessioni(Map<String, Object> filtro) throws DataAccessException;
	public Connessione insertConnessione(Connessione con) throws DataAccessException;
	public Connessione updateConnessione(Connessione con) throws DataAccessException;
	public boolean deleteConnessione(Connessione con) throws DataAccessException;
}
