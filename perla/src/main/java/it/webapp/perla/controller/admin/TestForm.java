package it.webapp.perla.controller.admin;

import it.webapp.perla.logic.UtentiLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller
public class TestForm extends AbstractController{

	@Autowired
	private UtentiLogic utentiLogic;
	

	@Override
	@RequestMapping ("/private/test")
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(request.getRequestURI());
		return new ModelAndView("hello");
	}
}
