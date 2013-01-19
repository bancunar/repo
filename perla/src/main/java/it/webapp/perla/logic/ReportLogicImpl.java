package it.webapp.perla.logic;

import it.webapp.perla.beans.Connessione;
import it.webapp.perla.dao.ReportDao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public class ReportLogicImpl implements ReportLogic {
	private ReportDao reportDao;

	public void setReportDao(ReportDao reportDao){
		this.reportDao=reportDao;
	}
	
	@Override
	public Connessione getConnessione(Connessione con) throws DataAccessException {
		return this.reportDao.getConnessione(con);
	}

	@Override
	public List<Connessione> getListaConnessioni(Map<String, Object> filtro) throws DataAccessException {
		return this.reportDao.getListaConnessioni(filtro);
	}

	@Override
	public Connessione insertConnessione(Connessione con) throws DataAccessException {
		return this.reportDao.insertConnessione(con);
	}

	@Override
	public Connessione updateConnessione(Connessione con) throws DataAccessException {
		return this.reportDao.updateConnessione(con);
	}

	@Override
	public boolean deleteConnessione(Connessione con) throws DataAccessException {
		this.reportDao.deleteConnessione(con);
		return true;
	}
	
	

}
