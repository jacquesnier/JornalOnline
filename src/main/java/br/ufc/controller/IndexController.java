package br.ufc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.service.SecaoService;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	SecaoService secaoService;
	
	private static final String TEMPLATE_INDEX = "index"; 
	
	@RequestMapping(method = RequestMethod.GET)
	public String index (Model model){
		model.addAttribute("listasecao", secaoService.list());
		return TEMPLATE_INDEX;
	}
}
