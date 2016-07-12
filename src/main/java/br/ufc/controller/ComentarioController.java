package br.ufc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufc.model.Comentario;
import br.ufc.model.Noticia;
import br.ufc.model.Pessoa;
import br.ufc.service.ComentarioService;
import br.ufc.service.NoticiaService;
import br.ufc.service.PessoaService;

@Controller
@RequestMapping(value="/comentario")
public class ComentarioController {
	
	@Autowired
	ComentarioService comentarioService;
	
	@Autowired
	NoticiaService noticiaService;
	
	@Autowired
	PessoaService pessoaService;

	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addComentario(HttpSession session, Comentario comentario, @RequestParam Long id_noticia){
		Noticia noticia = noticiaService.get(id_noticia);
		Pessoa pessoa = (Pessoa) session.getAttribute("PESSOA_LOGADA");
		if(noticia!=null && pessoa!=null && pessoa.isLeitor()){
			comentario.setAutor(pessoa);
			comentario.setNoticia(noticia);
			comentarioService.addOrUpdate(comentario);
			return "redirect:/noticia/"+id_noticia;
		}	
		return "redirect:/noticia/"+id_noticia;
	}
}
