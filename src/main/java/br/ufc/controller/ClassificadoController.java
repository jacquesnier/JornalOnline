package br.ufc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.model.Classificado;
import br.ufc.model.Oferta;
import br.ufc.model.Pessoa;
import br.ufc.service.ClassificadoService;
import br.ufc.service.PessoaService;

@Controller
@RequestMapping(value="/classificado")
public class ClassificadoController {
	
	@Autowired
	ClassificadoService classificadoService;
	
	@Autowired
	PessoaService pessoaService;
	
	public static final String TEMPLATE_ADDCLASSIFICADO = "/classificado/cadastro_classificado";
	public static final String TEMPLATE_LISTARCLASSIFICADO = "/classificado/listaclassificados";
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addClassificado(Model model){
		model.addAttribute("classificado", new Classificado());
		return TEMPLATE_ADDCLASSIFICADO;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addClassificado(Classificado classificado){
		//classificado.setData(new Date(System.currentTimeMillis()));
		classificadoService.addOrUpdate(classificado);
		return TEMPLATE_LISTARCLASSIFICADO;
	} 
	
	@RequestMapping(method = RequestMethod.GET)
	public String getClassificado (HttpSession session, Model model){
		model.addAttribute("listaclassificado", classificadoService.list());
		model.addAttribute("oferta", new Oferta());
		if(session.getAttribute("PESSOA_LOGADA")  != null){
			Pessoa pessoa = (Pessoa) session.getAttribute("PESSOA_LOGADA");
			if(pessoa.isLeitor()){
				model.addAttribute("pessoa", pessoa);
			}
		}
		return TEMPLATE_LISTARCLASSIFICADO;
	}
}
