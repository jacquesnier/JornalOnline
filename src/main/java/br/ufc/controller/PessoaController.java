package br.ufc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.model.Papel;
import br.ufc.model.Pessoa;
import br.ufc.service.PapelJornalService;
import br.ufc.service.PessoaService;

@Controller
@RequestMapping(value="/pessoa")
public class PessoaController {
	
	@Autowired
	PessoaService pessoaService;
	
	@Autowired
	PapelJornalService pJService;
	
	private final static String TEMPLATE_ADDLEITOR = "pessoa/cadastrarleitor";
	private final static String TEMPLATE_ADDJORNALISTA = "pessoa/cadastrarjornalista";
	
	@RequestMapping(value="/jornalista/add", method = RequestMethod.GET)
	public String addJornalista(Model model){
		model.addAttribute("jornalista", new Pessoa());
		return TEMPLATE_ADDJORNALISTA;
	}
	
	@RequestMapping(value="/jornalista/add", method = RequestMethod.POST)
	public String addJornalista(Pessoa jornalista){
		Pessoa pessoa = pessoaService.getPessoaByEmail(jornalista.getEmail());
		if(pessoa!=null){
			if(!pessoa.isJornalista()){
				pJService.addOrUpdate(pessoa, Papel.JORNALISTA);
				return "redirect:/pessoa/list";	
			}else{
				return "redirect:/pessoa/list";
			}
		}else{
			pessoaService.addOrUpdate(jornalista);
			pJService.addOrUpdate(jornalista, Papel.JORNALISTA);
			return "redirect:/pessoa/list";
		}
	}
	
	@RequestMapping(value="/leitor/add", method = RequestMethod.GET)
	public String addLeitor(Model model){
		model.addAttribute("leitor", new Pessoa());
		return TEMPLATE_ADDLEITOR;
	}
	
	@RequestMapping(value="/leitor/add", method = RequestMethod.POST)
	public String addLeitor(Pessoa leitor){
		Pessoa pessoa = pessoaService.getPessoaByEmail(leitor.getEmail());
		if(pessoa!=null){
			if(!pessoa.isLeitor()){
				pJService.addOrUpdate(pessoa, Papel.LEITOR);
				return "redirect:/pessoa/list";	
			}else{
				return "redirect:/pessoa/list";
			}
		}else{
			pessoaService.addOrUpdate(leitor);
			pJService.addOrUpdate(leitor, Papel.LEITOR);
			return "redirect:/pessoa/list";
		}
	}	

}
