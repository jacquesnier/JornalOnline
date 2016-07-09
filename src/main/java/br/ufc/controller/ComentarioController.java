package br.ufc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/comentario")
public class ComentarioController {

	@RequestMapping(value="/{id_noticia}/add", method = RequestMethod.POST)
	public String addComentario(Model model, @RequestParam Long id_noticia){
		return "redirect:/noticia/"+id_noticia;
	}
}
