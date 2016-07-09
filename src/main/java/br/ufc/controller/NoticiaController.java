package br.ufc.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.model.Noticia;
import br.ufc.model.Pessoa;
import br.ufc.model.Secao;
import br.ufc.service.NoticiaService;
import br.ufc.service.PessoaService;
import br.ufc.service.SecaoService;

@Controller
@RequestMapping("/noticia")
public class NoticiaController {
	
	@Autowired
	NoticiaService noticiaService;
	
	@Autowired
	PessoaService pessoaService;
	
	@Autowired
	SecaoService secaoService;
	
	private final static String TEMPLATE_ADDNOTICIA = "noticia/add_or_edit";
	private final static String TEMPLATE_LISTANOTICIA = "noticia/list";
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addNoticia(Model model){
		model.addAttribute("noticia", new Noticia());
		return TEMPLATE_ADDNOTICIA;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addNoticia(Noticia noticia){
		Pessoa autor = pessoaService.get(Long.valueOf(1));
		Secao secao = secaoService.get(Long.valueOf(6));
		
		noticia.setData(new Date(System.currentTimeMillis()));
		noticia.setAutor(autor);
		noticia.setSecao(secao);
		noticiaService.addOrUpdate(noticia);
		return "redirect:/noticia/list";
	} 
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String updateNoticia(Model model, @PathVariable Long id){
		Noticia noticia = noticiaService.get(id);
		if(noticia!=null){
			model.addAttribute("noticia", noticia);
			return TEMPLATE_ADDNOTICIA;
		}
		return "redirect:noticia/list";
	}
	
	@RequestMapping(value="/excluir/{id}", method = RequestMethod.GET)
	public String excluirNoticia (@PathVariable Long id){
		Noticia noticia = noticiaService.get(id);
		if(noticia!=null){
			noticiaService.delete(id);
			return "redirect:/noticia/list";
		}
		return "redirect:/noticia/list";
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String listarNoticias (Model model){
		model.addAttribute("listanoticia", noticiaService.list());
		return TEMPLATE_LISTANOTICIA;
	}
	
	@RequestMapping(value="/jornalista/{id}/listar", method = RequestMethod.GET)
	public String listarNoticiasJornalista (Model model, @PathVariable Long id){
		Pessoa pessoa = pessoaService.get(id);
		if(pessoa!=null && pessoa.isJornalista()){
			model.addAttribute("listanoticia", noticiaService.getNoticiasByJornalista(pessoa));
			return TEMPLATE_LISTANOTICIA;
		}
		return TEMPLATE_ADDNOTICIA;
	}
}
