package it.webapp.perla.dao.ibatis;

import it.webapp.perla.beans.Connessione;
import it.webapp.perla.dao.ReportDao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class SqlMapReportDao extends SqlMapClientDaoSupport implements
		ReportDao {

	@Override
	public Connessione getConnessione(Connessione con) throws DataAccessException {
		return (Connessione) getSqlMapClientTemplate().queryForObject("getConnessioniById", con);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Connessione> getListaConnessioni(Map<String, Object> filtro) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getConnessioniByFiltro",filtro);
	}

	@Override
	public Connessione insertConnessione(Connessione con) throws DataAccessException {
		getSqlMapClientTemplate().insert("insertConnessione",con);
		return con;
	}

	@Override
	public Connessione updateConnessione(Connessione con) throws DataAccessException {
		getSqlMapClientTemplate().update("updateConnessione",con);
		return con;
	}

	@Override
	public boolean deleteConnessione(Connessione con) throws DataAccessException {
		getSqlMapClientTemplate().delete("deleteConnessione",con);
		return false;
	}

}
