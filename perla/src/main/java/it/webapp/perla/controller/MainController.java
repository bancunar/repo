package it.webapp.perla.controller;

import it.webapp.perla.beans.Ruolo;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles and retrieves the common or admin page depending on the URI template.
 * A user must be log-in first he can access these pages.  Only the admin can see
 * the adminpage, however.
 */
@Controller
public class MainController {

	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/switchAuthHome.do", method = RequestMethod.GET)
    public String showHome(HttpServletRequest request) {
    	logger.debug(">>>>>>>>>>> LOGIN EFFETTUATO - DISPATCHER ACCESS");
    	
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String nextView="redirect:/public/showHome.do"; // Redirect homepage public
        if (authentication != null) {
        	if (authentication.isAuthenticated()){
        		// Verifica i ruoli
        		String ruoli=authentication.getAuthorities().toString();
        		if (ruoli.indexOf(Ruolo.ROLE_ADMIN)!=-1){
        			nextView="redirect:/private/showHome.do";
        		}else if (ruoli.indexOf(Ruolo.ROLE_CONTROLLER)!=-1){
        			nextView="redirect:/controller/showHome.do";
        		}else { // if (ruoli.indexOf(Ruolo.ROLE_ADMIN)!=-1){
        			
        		}
        	}
        }
    	return nextView;
	}
	
	/**
	 * Handles and retrieves the common JSP page that everyone can see
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/public/showHome.do", method = RequestMethod.GET)
    public String showPublicHome() {
    	logger.debug(">>>>>>>>>>> LOGIN EFFETTUATO - PUBLIC ACCESS");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/home.jsp
    	return "home";
	}
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/private/showHome.do", method = RequestMethod.GET)
    public String showPrivateHome() {
    	logger.debug(">>>>>>>>>>> LOGIN EFFETTUATO - PRIVATE ACCESS");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/home.jsp
    	return "home";
	}
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/controller/showHome.do", method = RequestMethod.GET)
    public String showControllerHome() {
    	logger.debug(">>>>>>>>>>> LOGIN EFFETTUATO - CONTROLLER ACCESS");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/home.jsp
    	return "home";
	}
}
