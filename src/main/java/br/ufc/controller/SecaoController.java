package br.ufc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.model.Secao;
import br.ufc.service.NoticiaService;
import br.ufc.service.SecaoService;

@Controller
@RequestMapping("/secao")
public class SecaoController {
	
	@Autowired
	SecaoService secaoService;
	
	@Autowired
	NoticiaService noticiaService;
	
	// criando constante referenciando caminho da pagina html
	private final static String TEMPLATE_ADDSECAO = "secao/cadastro_secao";
	private final static String TEMPLATE_LISTASECAO = "secao/listar";
	private final static String TEMPLATE_LISTANOTICIAS = "secao/secao";
	
	// chamar pagina secao, atenção para recebimento do model
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addSecao(Model model){
		model.addAttribute("secao", new Secao());
		return TEMPLATE_ADDSECAO;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addSecao(Secao secao){
		secaoService.addOrUpdate(secao);
		return "redirect:/";
	}
	
	@RequestMapping(value="/excluir/{id}", method = RequestMethod.GET)
	public String excluirSecao(@PathVariable Long id){
		Secao secao = secaoService.get(id);
		if(secao!=null){
			secaoService.delete(id);
			return "redirect:/secao/list";
		}
		return "redirect:/secao/list";
	}
	
	//Model serve para colocar algo na pagina
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String listarSecao(Model model){
		model.addAttribute("listasecao", secaoService.list());
		return TEMPLATE_LISTASECAO;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public String listarNoticiasSecao(Model model, @PathVariable Long id){
		Secao secao = secaoService.get(id);
		if(secao!=null){
			model.addAttribute("listanoticia", noticiaService.getNoticiasBySecao(secao));
			System.out.println(noticiaService.getNoticiasBySecao(secao).size());
			model.addAttribute("secao", secao);
		return TEMPLATE_LISTANOTICIAS;
		}
		return "redirect:/secao/list";
	}
}
