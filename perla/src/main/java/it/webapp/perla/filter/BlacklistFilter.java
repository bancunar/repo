package it.webapp.perla.filter;

import it.webapp.perla.logic.UtentiLogic;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * A custom filter that denies access if the given username is equal to
 * <b>mike</b>. This filter extends the {@link OncePerRequestFilter} to
 * guarantee that this filter is executed just once.
 * <p>
 * When the user enters this filter, he is already authenticated. This
 * filters acts like an intercept-url where you can customize access levels
 * per user
 *
 */
public class BlacklistFilter extends OncePerRequestFilter {
	protected static Logger logger = Logger.getLogger("filter");
	
	@Autowired
	private UtentiLogic utentiLogic;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		logger.debug("Running blacklist filter");
		
		/*
		String pathInfo=request.getPathInfo();
		String contextPath=request.getContextPath();
		String name=request.getLocalName();
		String address=request.getLocalAddr();
		String protocol=request.getProtocol();
		String header=request.getHeader("http");
		int port=request.getLocalPort();
		*/
		String uri=request.getRequestURI();
		String qString=request.getQueryString();
		logger.info(">>>> Request: "+uri+((qString!=null)?"?"+qString:""));
		
		// Retrieve user details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Filter only if user details is not empty; otherwise there's nothing to filter
        if (authentication != null) {
        	// If the username is equal to mike, deny access
	        /* if (authentication.getName().equals("mike") == true ) {
	        	logger.error( "Username and password match. Access denied!");
	            throw new AccessDeniedException("Username and password match. Access denied!");
	        }*/
        	/*
        	if (!authentication.getName().equals("anonymousUser")){
	        	@SuppressWarnings("unchecked")
				Map<String, UtenteSession> mapUtenti= (Map<String, UtenteSession>) WebUtils.getSessionAttribute(request, "_mapUtenti_");
	        	String idSessione=request.getSession().getId();
	        	if (mapUtenti==null){
	        		mapUtenti = new HashMap<String, UtenteSession>();
	        		UtenteSession uts=new UtenteSession(utentiLogic.getUtenteForAuthentication(authentication.getName()));
	        		uts.setIdSessione(idSessione);
	        		mapUtenti.put(authentication.getName(),uts);
	        	}else{
	        		UtenteSession uts=mapUtenti.get(authentication.getName());
	        		if (uts!=null && !uts.getIdSessione().equals(idSessione)){ // utente già autenticato con una sessione diversa quindi forzare il logout
	        			logger.error("Utente è attualemnte operativo con un'altra sessione, chiuderla e accedere nuovamente");
	    	            throw new AccessDeniedException("Utente è attualemnte operativo con un'altra sessione, chiuderla e accedere nuovamente");
	        		}
	        	}
	        	WebUtils.setSessionAttribute(request, "_mapUtenti_",mapUtenti);
        	}
        	*/
        }
        
        // User details are not empty
        logger.debug("Continue with remaining filters");
        filterChain.doFilter(request, response);
	}

}
