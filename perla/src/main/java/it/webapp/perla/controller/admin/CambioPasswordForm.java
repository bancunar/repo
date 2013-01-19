package it.webapp.perla.controller.admin;

import it.webapp.perla.MessageBundleCustom;
import it.webapp.perla.beans.Utente;
import it.webapp.perla.logic.UtentiLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CambioPasswordForm {
	@Autowired
	private UtentiLogic utentiLogic;
	
	@Autowired
	private MessageBundleCustom msgBundle;
	
	// Lista con GET
	@RequestMapping(value = "/*/cambioPassword.do", method = RequestMethod.GET)
	public String cambioPassword(@RequestParam(value="user", required = false) String user, ModelMap model, HttpServletRequest request, HttpServletResponse respone) {
		Utente ut=new Utente();
		try {
			if (user != null && !user.equals("")) { //.matches("[0-9]+"
				ut.setUserName(user);
				ut = utentiLogic.getUtente(ut);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg_error",e.getMessage());
			model.put("ok_action",false);
		}
		// model.put("ruoli",Ruolo.getListaRuoli());
		model.addAttribute("utente", ut);
		return "utenti/cambioPasswordForm";
	}
 	
	// Bind dei paramentri nell'oggetto specificato in MODELATTRIBUTE, e Submit se non ci sono errori
	@RequestMapping(value = "/*/cambioPassword.do", method = RequestMethod.POST)
	public String submitForm(HttpServletRequest request, @ModelAttribute("utente") Utente ut, BindingResult result, ModelMap model) {
		Md5PasswordEncoder passwordEncoder=new Md5PasswordEncoder();
		
		String vecchiaPass=request.getParameter("vecchiaPass");
		String nuovaPass=request.getParameter("nuovaPass");
		if (vecchiaPass==null || nuovaPass==null || vecchiaPass.equals("") || nuovaPass.equals("") || vecchiaPass.equals(nuovaPass)){
			((Errors)result).reject("ERRORE_0", msgBundle.getMessage("utente_cambio_pass_1"));
		}else if (passwordEncoder.isPasswordValid(ut.getUserName(), vecchiaPass, ut.getUserName())){
			((Errors)result).reject("ERRORE_1", msgBundle.getMessage("utente_cambio_pass_2"));
		}else if (ut.getUserName().equals(nuovaPass)){
			((Errors)result).reject("ERRORE_3", msgBundle.getMessage("utente_cambio_pass_3"));
		}
		
		if (result.hasErrors()) {
			model.put("ok_action",false);
			return "utenti/cambioPasswordForm";
		}else{
			try {
				ut.setPassword(nuovaPass);
				this.utentiLogic.updateUtenteCambioPassword(ut);
				model.put("ok_action",true);
				model.put("id", ut.getId());
			} catch (Exception e) {
				e.printStackTrace();
				// model.addAttribute("msg_error",e.getMessage());
				model.put("ok_action",false);
				return "utenti/cambioPasswordForm";
			}
		}
		return "redirect:/logout.do";
	} 
}
