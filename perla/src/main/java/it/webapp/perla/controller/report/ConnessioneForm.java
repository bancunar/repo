package it.webapp.perla.controller.report;

import it.webapp.perla.beans.Connessione;
import it.webapp.perla.logic.ReportLogic;

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
// @SessionAttributes ("connessione")
public class ConnessioneForm {

	@Autowired
	private ReportLogic reportLogic;
	
	// Lista con GET
	@RequestMapping(value = "/private/showListaConnessioni.do", method = RequestMethod.GET)
	public String showFormList(ModelMap model, HttpServletRequest request, HttpServletResponse respone) {
		Map<String, Object> filtro=new HashMap<String, Object>();
		List<Connessione> lista = new ArrayList<Connessione>();
		try {
			lista=reportLogic.getListaConnessioni(filtro);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg_error",e.getMessage());
			model.put("ok_action",false);
		}finally{ // Carico eventuali errori dovuti a qualche submit precedente
			// model.addAttribute("msg_error",request.getParameter("msg_error"));
			model.addAttribute("ok_action",request.getParameter("ok_action"));
		}
		model.addAttribute("lista", lista);
		return "report/connessioniList";
	}
	
	// Lista con POST usabile per passare in sessione il filtro
	@RequestMapping(value = "/private/showListaConnessioni.do", method = RequestMethod.POST)
	public String showFormListFiltro(@ModelAttribute("filtro") Connessione filtroConnessione, ModelMap model) {
		Map<String, Object> filtro=new HashMap<String, Object>();
		List<Connessione> lista = new ArrayList<Connessione>();
		try {
			lista=reportLogic.getListaConnessioni(filtro);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg_error",e.getMessage());
			model.put("ok_action",false);
		}
		model.addAttribute("lista", lista);
		return "report/connessioniList";
	}
	
	
	// Azione singola per un Oggetto con passaggio parametri in GET (equivalente di formBackingObject e referenceData
	@RequestMapping(value = "/private/showConnessione.do", method = RequestMethod.GET)
	public String showFormObject(@RequestParam(value="id", required = false) Integer id, ModelMap model) {
		Connessione con=new Connessione();
		try {
			if (id != null) { //.matches("[0-9]+"
				con.setId(id.intValue());
				con = reportLogic.getConnessione(con);
			}else{
				con=new Connessione();
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg_error",e.getMessage());
			model.put("ok_action",false);
		}
		model.addAttribute("connessione", con);
		return "report/connessioneForm";
	}
 
	// Bind dei paramentri nell'oggetto specificato in MODELATTRIBUTE, e Submit se non ci sono errori
	@RequestMapping(value = "/private/showConnessione.do", method = RequestMethod.POST)
	public String submitForm(HttpServletRequest request, @ModelAttribute("connessione") Connessione con, BindingResult result, ModelMap model) {
		if (con.getNome()==null || con.getNome().equals("")){
			((Errors)result).reject("ERRORE_1", "Specificare un Nome");
		}
		if (con.getUrl()==null || con.getUrl().equals("")){
			((Errors)result).reject("ERRORE_1", "Specificare una URL");
		}
		if (con.getDriver()==null || con.getDriver().equals("")){
			((Errors)result).reject("ERRORE_1", "Specificare un Driver");
		}

		if (result.hasErrors()) {
			model.put("ok_action",false);
			return "report/connessioneForm";
		}else{
			try {
				if (con.getId()>0)
					this.reportLogic.updateConnessione(con);
				else
					this.reportLogic.insertConnessione(con);
				model.put("ok_action",true);
				model.put("id", con.getId());
			} catch (Exception e) {
				e.printStackTrace();
				// model.addAttribute("msg_error",e.getMessage());
				model.put("ok_action",false);
				return "redirect:/private/showListaConnessioni.do";
			}
		}
		return "redirect:/private/showConnessione.do";
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
	@RequestMapping(value = "/private/deleteConnessione.do", method = RequestMethod.GET)
	public String deleteUtente(@RequestParam(value="id", required = false) Integer id, ModelMap model, HttpServletRequest request, HttpServletResponse respone) {
		Connessione con=new Connessione();
		try {
			if (id != null) { //.matches("[0-9]+"
				con.setId(id.intValue());
				reportLogic.deleteConnessione(con);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// model.addAttribute("msg_error",e.getMessage());
			model.put("ok_action",false);
		}
		return "redirect:/private/showListaConnessioni.do";
	}
}
