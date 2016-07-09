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
	private final static String TEMPLATE_ADDSECAO = "secao/add_or_edit";
	private final static String TEMPLATE_LISTASECAO = "secao/listar";
	private final static String TEMPLATE_LISTANOTICIAS = "secao/listanoticias";
	
	// chamar pagina secao, atenção para recebimento do model
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addSecao(Model model){
		model.addAttribute("secao", new Secao());
		return TEMPLATE_ADDSECAO;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addSecao(Secao secao){
		secaoService.addOrUpdate(secao);
		return "redirect:/secao/list";
	}
	//@PathVariable serve para pegar o atributo que eu passei na url
	// verifico se é nulo vou para a pagina de add, senao atualizo a pagina carregando objeto
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String updateSecao(Model model, @PathVariable Long id){
		Secao secao = secaoService.get(id);
		if(secao!=null){
			model.addAttribute("secao", secao);
			return TEMPLATE_ADDSECAO;
		} 
		return "redirect:/secao/list";
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
		return TEMPLATE_LISTANOTICIAS;
		}
		return "redirect:/secao/list";
		
	}
}
