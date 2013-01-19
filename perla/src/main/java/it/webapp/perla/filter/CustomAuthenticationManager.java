package it.webapp.perla.filter;

import it.webapp.perla.beans.Utente;
import it.webapp.perla.logic.UtentiLogic;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * A custom authentication manager that allows access if the user details
 * exist in the database and if the username and password are not the same.
 * Otherwise, throw a {@link BadCredentialsException}
 */
public class CustomAuthenticationManager implements AuthenticationManager {

	protected static Logger logger = Logger.getLogger("service");

	// Our custom DAO layer
	@Autowired
	private UtentiLogic utentiLogic;
	// We need an Md5 encoder since our passwords in the database are Md5 encoded. 
	private Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
	
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		logger.debug("Autenticazione in corso ...");
		
		// Init a database user object
		Utente ut = new Utente();
		ut.setUserName(auth.getName());
		try {
			// Retrieve user details from database
			ut = utentiLogic.getUtenteForAuthentication(auth.getName());
		} catch (Exception e) {
			logger.error("Utente non registrato");
			throw new BadCredentialsException("Utente non registrato");
		}
		
		if (ut==null && auth.getName().equals("start")){ // Utente iniziale
			List<Utente> lu=utentiLogic.getListaUtenti(new HashMap<String, Object>());
			if (lu==null || lu.isEmpty()){ // non ci sono utenti quindi creo utente 'admin'
				this.utentiLogic.insertStartAdmin();
				throw new BadCredentialsException("Utente ADMIN creato. Al primo ingresso modificare password!");
			}
		}
		
		try {
			// Compare passwords
			// Make sure to encode the password first before comparing
			
			if (auth!=null && auth.getName()!=null && auth.getName().equals("")){
				String pass=passwordEncoder.encodePassword((String) auth.getCredentials(), ut.getUserName());
				System.out.println(pass.equals((String) auth.getCredentials()));
			}
			if (  passwordEncoder.isPasswordValid(ut.getPassword(), (String) auth.getCredentials(), ut.getUserName()) == false ) {
				logger.debug("Password non valida!");
				throw new BadCredentialsException("Password non valida!");
			}
			
			/* 
			if ( !ut.getPassword().equals((String) auth.getCredentials()) ) { // senza nessun Encoder
				logger.debug("Password errata!");
				throw new BadCredentialsException("Password errata!");
			}
			*/
			// Here's the main logic of this custom authentication manager
			// Username and password must be the same to authenticate
			if (auth.getName().equals(auth.getCredentials()) == true) {
				logger.debug("Username e Password non possono essere uguali!");
				throw new BadCredentialsException("Username e Password non possono essere uguali!");
			} else {
				logger.debug("Autenticazione eseguita");
				return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), ut.getAuthoritiesEnabled());
			}
		}catch (Exception e) {
				logger.debug("Autenticazione fallita!");
				throw new BadCredentialsException("Autenticazione fallita!");
		}
	}
	
	
	/**
	 * Retrieves the correct ROLE type depending on the access level, where access level is an Integer.
	 * Basically, this interprets the access value whether it's for a regular user or admin.
	 * 
	 * @param access an integer value representing the access of the user
	 * @return collection of granted authorities
	 */
	 /*public Collection<GrantedAuthority> getAuthorities(Integer access) {
			// Create a list of grants for this user
			List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
			
			// All users are granted with ROLE_USER access
			// Therefore this user gets a ROLE_USER by default
			logger.debug("Grant ROLE_USER to this user");
			authList.add(new SimpleGrantedAuthority("ROLE_USER"));
			
			// Check if this user has admin access 
			// We interpret Integer(1) as an admin user
			if ( access.compareTo(1) == 0) {
				// User has admin access
				logger.debug("Grant ROLE_ADMIN to this user");
				authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}

			// Return list of granted authorities
			return authList;
	  }*/

}
