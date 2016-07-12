package br.ufc.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufc.PasswordEncryption;
import br.ufc.model.Papel;
import br.ufc.model.PapelJornal;
import br.ufc.model.Pessoa;
import br.ufc.service.PessoaService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	PessoaService servicePessoa;

	@Autowired
	Environment env;

	private final static String TEMPLATE_EDITOR = "pessoa/gerenciador_editor";
	private final static String TEMPLATE_JORNALISTA = "pessoa/gerenciador_jornalista";

	@RequestMapping(method = RequestMethod.POST)
	public String login (HttpSession session, @RequestParam Integer papel, @RequestParam String login
			, @RequestParam String senha){
		
		if(papel == 1){
			String loginEditor = env.getProperty("editor.login");	
			String senhaEditor = env.getProperty("editor.senha"); 
			
			System.out.println("Entrei caralho: " + loginEditor + senhaEditor);
			
			if(loginEditor.equals(login) && senhaEditor.equals(senha)){
				String nomeEditor = env.getProperty("editor.nome");
				String emailEditor = env.getProperty("editor.email");
				
				Pessoa editor = montarEditor(nomeEditor, loginEditor, emailEditor, senhaEditor);
				
				session.setAttribute("PESSOA_LOGADA", editor);
				return "redirect:/pessoa/editor/gerenciador";
			}
		}
		
		senha = PasswordEncryption.encrypt(senha);
		Pessoa pessoaAux = servicePessoa.getPessoaByLoginAndSenha(login, senha); 
		if(pessoaAux!=null){
			switch (papel) {
			case 0:
				if(pessoaAux.isLeitor()){
					session.setAttribute("PESSOA_LOGADA", pessoaAux);
					return "redirect:/noticia/list";
				}else{
					return "redirect:/";
				}
			case 2:
				if(pessoaAux.isJornalista()){
					session.setAttribute("PESSOA_LOGADA", pessoaAux);
					return "redirect:/pessoa/jornalista/gerenciador";
				}else{
					return "redirect:/";
				}
				//break;
			default:
				return "redirect:/";
			}
			
		}
		
		return "redirect:/";
	}

	private static Properties getProp() throws IOException {
		Properties propriedade = new Properties();
		FileInputStream arquivo = new FileInputStream(
				"./resources/application.preopeties");
		propriedade.load(arquivo);
		return propriedade;
	}
	
	private Pessoa montarEditor(String nome, String login, String email, String senha){
		Pessoa pessoa  = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setDescricao("legal");
		pessoa.setEmail(email);
		pessoa.setLogin(login);
		pessoa.setSenha(senha);
		
		List<PapelJornal> papeis = new ArrayList<PapelJornal>();
		PapelJornal papelJornal = new PapelJornal();
		papelJornal.setPapel(Papel.EDITOR);
		papelJornal.setPessoa(pessoa);
		papeis.add(papelJornal);
		
		pessoa.setPapeis(papeis);
		
		return pessoa;
	}
	
	
}