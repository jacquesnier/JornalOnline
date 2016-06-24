package br.ufc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = {"/noticia"})
public class NoticiaController {
	
	//@Autowired
	//private NoticiaService noticiaService;
	
	//private static final String TEMPLATE_ADD_OR_EDIT = "noticia/add_or_edit";
	private static final String TEMPLATE_LIST = "noticia/list";

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getNoticia(Model model) {
		//List<Noticia> noticiaList = noticiaService.list();
		//model.addAttribute("noticiaList", noticiaList);
		return TEMPLATE_LIST;
	}

}
