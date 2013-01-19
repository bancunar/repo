package it.webapp.perla.controller.test;

import it.webapp.perla.logic.UtentiLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/helloWorld")
public class HelloController { 
	@Autowired
	private UtentiLogic utentiLogic;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		model.addAttribute("message", "Hello World!");
	return "hello";
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public String helloWorld(@PathVariable("id") int id, Model model) {
		model.addAttribute("message", "Hello World!");
	return "hello";
	}
}
