package it.webapp.perla.logic;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import it.webapp.perla.beans.Connessione;

public interface ReportLogic {
	public Connessione getConnessione(Connessione con) throws DataAccessException;
	public List<Connessione> getListaConnessioni(Map<String, Object> filtro) throws DataAccessException;
	public Connessione insertConnessione(Connessione con) throws DataAccessException;
	public Connessione updateConnessione(Connessione con) throws DataAccessException;
	public boolean deleteConnessione(Connessione con) throws DataAccessException;
}
