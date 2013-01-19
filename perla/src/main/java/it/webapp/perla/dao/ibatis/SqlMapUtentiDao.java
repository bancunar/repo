package it.webapp.perla.dao.ibatis;

import java.util.List;
import java.util.Map;

import it.webapp.perla.beans.Utente;
import it.webapp.perla.dao.UtentiDao;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class SqlMapUtentiDao extends SqlMapClientDaoSupport implements UtentiDao {

	@Override
	public Utente getUtenteForAuthentication(String username) throws DataAccessException {
		return (Utente) getSqlMapClientTemplate().queryForObject("getUtenteForAuthentication", username);
	}
	
	@Override
	public Utente getUtente(Utente ut) throws DataAccessException {
		return (Utente) getSqlMapClientTemplate().queryForObject("getUtenteByIdOrUserName", ut);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Utente> getListaUtenti(Map<String, Object> filtro) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getUtentiByFiltro",filtro);
	}

	@Override
	public Utente insertStartAdmin() throws DataAccessException {
		Utente ut=new Utente();
		ut.setId(0);
		ut.setUserName("admin");
		ut.setPassword("start");
		ut.setAttivo(true);
		ut.setNome("admin");
		ut.setCognome("admin");
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		ut.setPassword(passwordEncoder.encodePassword(ut.getPassword(), ut.getUserName()));
		getSqlMapClientTemplate().insert("insertStartAdmin",ut);
		return ut;
	}
	
	@Override
	public Utente insertUtente(Utente ut) throws DataAccessException {
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		ut.setPassword(passwordEncoder.encodePassword(ut.getPassword(), ut.getUserName()));
		getSqlMapClientTemplate().insert("insertUtente",ut);
		return ut;
	}

	@Override
	public Utente updateUtente(Utente ut) throws DataAccessException {
		// Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		// ut.setPassword(passwordEncoder.encodePassword(ut.getPassword(), ut.getUserName()));
		getSqlMapClientTemplate().update("updateUtente",ut);
		return ut;
	}

	@Override
	public boolean deleteUtente(Utente ut) throws DataAccessException {
		getSqlMapClientTemplate().delete("deleteUtente",ut);
		return false;
	}

	@Override
	public void updateUtenteCambioPassword(Utente ut) throws DataAccessException {
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		ut.setPassword(passwordEncoder.encodePassword(ut.getPassword(), ut.getUserName()));
		getSqlMapClientTemplate().update("updateUtenteCambioPassword",ut);
	}

}
