package it.webapp.perla.controller;


import it.webapp.perla.MessageBundleCustom;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles and retrieves the login or denied page depending on the URI template
 */
@Controller
public class LoginLogoutController {
        
	protected static Logger logger = Logger.getLogger("controller");
	
	@Autowired
	private MessageBundleCustom msgBundle;
	
	/**
	 * Handles and retrieves the login JSP page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value="error", required=false) boolean error, ModelMap model) {
		logger.debug(">>>>>>>>>>> LOGIN");
		
		/*String pathInfo=request.getPathInfo();
		String uri=request.getRequestURI();
		String contextPath=request.getContextPath();
		String qString=request.getQueryString();
		String name=request.getLocalName();
		String address=request.getLocalAddr();
		String protocol=request.getProtocol();
		String header=request.getHeader("http");
		int port=request.getLocalPort();
		
		logger.info(">>>> Request: ");*/
		
		// Add an error message to the model if login is unsuccessful
		// The 'error' parameter is set to true based on the when the authentication has failed. 
		// We declared this under the defaultFailureUrl attribute inside the spring-security.xml

		if (error) {
			// Assign an error message
			model.put("error", msgBundle.getMessage("login_error"));
		} else {
			model.put("error", "");
		}
		
		// This will resolve to /WEB-INF/jsp/login.jsp
		return "login";
	}
	
	/**
	 * Handles and retrieves the denied JSP page. This is shown whenever a regular user
	 * tries to access an admin only page.
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/denied.do", method = RequestMethod.GET)
 	public String getDeniedPage() {
		logger.debug(">>>>>>>>>>> LOGIN - ACCESSO NEGATO");
		
		// This will resolve to /WEB-INF/jsp/denied.jsp
		return "denied";
	}
}