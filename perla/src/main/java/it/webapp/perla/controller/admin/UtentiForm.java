package it.webapp.perla.controller.admin;

import it.webapp.perla.MessageBundleCustom;
import it.webapp.perla.beans.Ruolo;
import it.webapp.perla.beans.Utente;
import it.webapp.perla.logic.UtentiLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
// @RequestMapping ("/private/showUtente")
// @SessionAttributes ("utente")
public class UtentiForm {

	@Autowired
	private UtentiLogic utentiLogic;
	@Autowired
	private MessageBundleCustom msgBundle;
	
	// Lista con GET
	@RequestMapping(value = "/private/showListaUtenti.do", method = RequestMethod.GET)
	public String showFormList(ModelMap model, HttpServletRequest request, HttpServletResponse respone) {
		Map<String, Object> filtro=new HashMap<String, Object>();
		List<Utente> lista = new ArrayList<Utente>();
		try {
			lista=utentiLogic.getListaUtenti(filtro);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg_error",e.getMessage());
			model.put("ok_action",false);
		}finally{ // Carico eventuali errori dovuti a qualche submit precedente
			// model.addAttribute("msg_error",request.getParameter("msg_error"));
			model.addAttribute("ok_action",request.getParameter("ok_action"));
		}
		model.addAttribute("lista", lista);
		return "utenti/utentiList";
	}
	
	// Lista con POST usabile per passare in sessione il filtro
	@RequestMapping(value = "/private/showListaUtenti.do", method = RequestMethod.POST)
	public String showFormListFiltro(@ModelAttribute("filtro") Utente filtroUtente, ModelMap model) {
		Map<String, Object> filtro=new HashMap<String, Object>();
		List<Utente> lista = new ArrayList<Utente>();
		try {
			lista=utentiLogic.getListaUtenti(filtro);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg_error",e.getMessage());
			model.put("ok_action",false);
		}
		model.addAttribute("lista", lista);
		return "utenti/utentiList";
	}
	
	// Azione singola per un Oggetto con passaggio parametri in GET (equivalente di formBackingObject e referenceData
	@RequestMapping(value = "/private/showUtente.do", method = RequestMethod.GET)
	public String showFormObject(@RequestParam(value="id", required = false) Integer id, ModelMap model) {
		Utente ut=new Utente();
		try {
			if (id != null) { //.matches("[0-9]+"
				ut.setId(id.intValue());
				if (id.equals(0)){
					ut.setUserName("admin");
				}
				ut = utentiLogic.getUtente(ut);
			}else{
				ut=new Utente();
				ut.setRuoli(Ruolo.getListaRuoli());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ut=new Utente();
			ut.setRuoli(Ruolo.getListaRuoli());
			model.addAttribute("msg_error",e.getMessage());
			model.put("ok_action",false);
		}
		// model.put("ruoli",Ruolo.getListaRuoli());
		model.addAttribute("utente", ut);
		return "utenti/utenteForm";
	}
 	
	// Bind dei paramentri nell'oggetto specificato in MODELATTRIBUTE, e Submit se non ci sono errori
	@RequestMapping(value = "/private/showUtente.do", method = RequestMethod.POST)
	public String submitForm(HttpServletRequest request, @ModelAttribute("utente") Utente ut, BindingResult result, ModelMap model) {
		if (ut.getUserName()==null || ut.getUserName().equals("")){
			((Errors)result).reject("ERRORE_1", msgBundle.getMessage("utente_form_error_1"));
		}else if (ut.getPassword()==null || ut.getPassword().equals("")){
			((Errors)result).reject("ERRORE_2", msgBundle.getMessage("utente_form_error_2"));
		}else if (ut.getUserName().equals(ut.getPassword())){
			((Errors)result).reject("ERRORE_3", msgBundle.getMessage("utente_form_error_3"));
		}else{
			int countRuoli=0;
			if (!ut.getRuoli().isEmpty()){
				for (int i=0;i<ut.getRuoli().size();i++){
					if (ut.getRuoli().get(i).isSelezionato()){
						countRuoli++;
						break;
					}
				}
			}
			if (countRuoli<=0){
				((Errors)result).reject("ERRORE_4", msgBundle.getMessage("utente_form_error_4"));
			}
		}
		
		if (result.hasErrors()) {
			model.put("ok_action",false);
			return "utenti/utenteForm";
		}else{
			try {
				if (ut.getId()>0 || ut.getUserName().equals("admin"))
					this.utentiLogic.updateUtente(ut);
				else
					this.utentiLogic.insertUtente(ut);
				model.put("ok_action",true);
				model.put("id", ut.getId());
			} catch (Exception e) {
				e.printStackTrace();
				// model.addAttribute("msg_error",e.getMessage());
				model.put("ok_action",false);
				return "redirect:/private/showListaUtenti.do";
			}
		}
		return "redirect:/private/showUtente.do";
	} 
	
	/*
	// InitBind dei paramentri analogo al metodo initBinder
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields(new String[] {"id"});
		dataBinder.setRequiredFields(new String[] {"name", "area", "population", "currency"});
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		dateFormat.setLenient(false);
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	*/
	
	// Eliminazione con GET
	@RequestMapping(value = "/private/deleteUtente.do", method = RequestMethod.GET)
	public String deleteUtente(@RequestParam(value="id", required = false) Integer id, ModelMap model, HttpServletRequest request, HttpServletResponse respone) {
		Utente ut=new Utente();
		try {
			if (id != null && id.intValue()>0) { //.matches("[0-9]+"
				ut.setId(id.intValue());
				utentiLogic.deleteUtente(ut);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// model.addAttribute("msg_error",e.getMessage());
			model.put("ok_action",false);
		}
		return "redirect:/private/showListaUtenti.do";
	}
}
