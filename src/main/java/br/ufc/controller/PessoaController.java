package br.ufc.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.model.Noticia;
import br.ufc.model.Papel;
import br.ufc.model.Pessoa;
import br.ufc.service.NoticiaService;
import br.ufc.service.PapelJornalService;
import br.ufc.service.PessoaService;

@Controller
@RequestMapping(value="/pessoa")
public class PessoaController {
	
	@Autowired
	PessoaService pessoaService;
	
	@Autowired
	PapelJornalService papelJornalService;
	
	@Autowired
	NoticiaService noticiaService;
	
	private final static String TEMPLATE_ADDLEITOR = "pessoa/cadastro_leitor";
	private final static String TEMPLATE_ADDJORNALISTA = "pessoa/cadastro_jornalista";
	private final static String TEMPLATE_GERENCIADORJORNALISTA = "pessoa/gerenciador_jornalista";
	private final static String TEMPLATE_GERENCIADOREDITOR = "pessoa/gerenciador_editor";
	
	@RequestMapping(value="/jornalista/add", method = RequestMethod.GET)
	public String addJornalista(Model model){
		model.addAttribute("jornalista", new Pessoa());
		return TEMPLATE_ADDJORNALISTA;
	}
	
	@RequestMapping(value="/jornalista/add", method = RequestMethod.POST)
	public String addJornalista(@RequestParam(value = "foto_pessoa", required = false) MultipartFile foto_pessoa,
			Pessoa jornalista){
		Pessoa pessoa = pessoaService.getPessoaByLogin(jornalista.getLogin());
		String foto = null;
		if(pessoa!=null){
			if(!pessoa.isJornalista()){
				if(!foto_pessoa.isEmpty()){
					try {
						Files.copy(foto_pessoa.getInputStream(), 
								Paths.get("src/main/resources/static/perfil", 
								foto_pessoa.getOriginalFilename()));
						foto = foto_pessoa.getOriginalFilename();
					} catch (IOException e){
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				pessoa.setFoto(foto);
				papelJornalService.addOrUpdate(pessoa, Papel.JORNALISTA);
				return "redirect:/";	
			}else{
				return "redirect:/";
			}
		}else{
			if(!foto_pessoa.isEmpty()){
				try {
					Files.copy(foto_pessoa.getInputStream(), Paths.get(
							"src/main/resources/static/perfil",
							foto_pessoa.getOriginalFilename()));
					foto = foto_pessoa.getOriginalFilename();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			jornalista.setFoto(foto);
			pessoaService.addOrUpdate(jornalista);
			papelJornalService.addOrUpdate(jornalista, Papel.JORNALISTA);
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/leitor/add", method = RequestMethod.GET)
	public String addLeitor(Model model){
		model.addAttribute("leitor", new Pessoa());
		return TEMPLATE_ADDLEITOR;
	}
	
	@RequestMapping(value="/leitor/add", method = RequestMethod.POST)
	public String addLeitor(@RequestParam(value = "foto_pessoa", required = false) MultipartFile foto_pessoa, 
			Pessoa leitor){
		Pessoa pessoa = pessoaService.getPessoaByLogin(leitor.getLogin());
		String foto = null;

		if(pessoa!=null){
			if(!pessoa.isLeitor()){
				if(!foto_pessoa.isEmpty()){
					try {
						Files.copy(foto_pessoa.getInputStream(), 
								Paths.get("src/main/resources/static/perfil", 
								foto_pessoa.getOriginalFilename()));
						foto = foto_pessoa.getOriginalFilename();
					} catch (IOException e){
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(foto);
				pessoa.setFoto(foto);
				papelJornalService.addOrUpdate(pessoa, Papel.LEITOR);
				return "redirect:/";
			}else{
				return "redirect:/";
			}
		}else{
			if(!foto_pessoa.isEmpty()){
				try {
					Files.copy(foto_pessoa.getInputStream(), Paths.get(
							"src/main/resources/static/perfil",
							foto_pessoa.getOriginalFilename()));
					foto = foto_pessoa.getOriginalFilename();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			leitor.setFoto(foto);
			pessoaService.addOrUpdate(leitor);
			papelJornalService.addOrUpdate(leitor, Papel.LEITOR);
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/jornalista/gerenciador", method = RequestMethod.GET)
	public String gerenciadorJornalista(HttpSession session, Model model){
		Pessoa pessoaAux = (Pessoa) session.getAttribute("PESSOA_LOGADA");
		List<Noticia> listaNoticia = noticiaService.getNoticiasByJornalista(pessoaAux);
		model.addAttribute("jornalista", pessoaAux);
		model.addAttribute("listanoticia", listaNoticia);
		return TEMPLATE_GERENCIADORJORNALISTA;
	}
	
	@RequestMapping(value="/editor/gerenciador", method = RequestMethod.GET)
	public String gerenciadorEditor(HttpSession session, Model model){
		Pessoa pessoaAux = (Pessoa) session.getAttribute("PESSOA_LOGADA");
		List<Noticia> listaNoticia = noticiaService.list();
		model.addAttribute("editor", pessoaAux);
		model.addAttribute("listanoticia", listaNoticia);
		return TEMPLATE_GERENCIADOREDITOR;
	}

}
