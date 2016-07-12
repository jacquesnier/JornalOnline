package br.ufc.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;

import javax.servlet.ServletContext;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;

import br.ufc.model.Comentario;
import br.ufc.model.Noticia;
import br.ufc.model.Pessoa;
import br.ufc.model.Secao;
import br.ufc.service.ComentarioService;
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

	@Autowired
	ComentarioService comentarioService;

	@Autowired
	private ServletContext servletContext;

	private final static String TEMPLATE_ADDNOTICIA = "noticia/cadastro_noticia";
	private final static String TEMPLATE_LISTANOTICIA = "noticia/list";
	private final static String TEMPLATE_NOTICIA = "noticia/noticia";

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addNoticia(Model model) {
		model.addAttribute("noticia", new Noticia());
		model.addAttribute("listasecao", secaoService.list());
		
		return TEMPLATE_ADDNOTICIA;
	}

	// requered=false é para deixar a foto como envio opcional.
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addNoticia(
			HttpSession session,
			@RequestParam(value = "foto_noticia", required = false) MultipartFile foto_noticia,
			Noticia noticia, @RequestParam String id_secao) {
		
		Long id = Long.valueOf(id_secao);
		Pessoa pessoa;
		String foto = null;
		System.out.println(foto_noticia);
		
		if (session.getAttribute("PESSOA_LOGADA") != null) {
			pessoa = (Pessoa) session.getAttribute("PESSOA_LOGADA");
			if (pessoa.isJornalista()) {
				Secao secao = secaoService.get(id);
				System.out.println(secao.getId());
				if (!foto_noticia.isEmpty()) {
					try {
						Files.copy(foto_noticia.getInputStream(), Paths.get(
								"src/main/resources/static/imagens",
								foto_noticia.getOriginalFilename()));
						foto = foto_noticia.getOriginalFilename();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				System.out.println(foto);
				noticia.setFoto(foto);
				noticia.setData(new Date(System.currentTimeMillis()));
				noticia.setAutor(pessoa);
				noticia.setSecao(secao);
				noticiaService.addOrUpdate(noticia);
			}
			return "redirect:/noticia/list";
		}
		return "redirect:/noticia/list";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String updateNoticia(Model model, @PathVariable Long id) {
		Noticia noticia = noticiaService.get(id);
		if (noticia != null) {
			model.addAttribute("noticia", noticia);
			return TEMPLATE_ADDNOTICIA;
		}
		return "redirect:noticia/list";
	}

	@RequestMapping(value = "/excluir/{id}", method = RequestMethod.GET)
	public String excluirNoticia(@PathVariable Long id) {
		System.out.println("passou");
		Noticia noticia = noticiaService.get(id);
		if (noticia != null) {
			noticiaService.delete(id);
			return "redirect:/noticia/list";
		}
		return "redirect:/noticia/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listarNoticias(Model model) {
		model.addAttribute("listanoticia", noticiaService.list());
		return TEMPLATE_LISTANOTICIA;
	}

	@RequestMapping(value = "/jornalista/{id}/listar", method = RequestMethod.GET)
	public String listarNoticiasJornalista(Model model, @PathVariable Long id) {
		Pessoa pessoa = pessoaService.get(id);
		if (pessoa != null && pessoa.isJornalista()) {
			model.addAttribute("listanoticia",
					noticiaService.getNoticiasByJornalista(pessoa));
			return TEMPLATE_LISTANOTICIA;
		}
		return TEMPLATE_ADDNOTICIA;
	}

	@RequestMapping(value = "/{id_noticia}", method = RequestMethod.GET)
	public String getNoticia(HttpSession session, Model model, @PathVariable Long id_noticia) {
		// TODO Refatorar metodo getNoticia
		model.addAttribute("noticia", noticiaService.get(id_noticia));
		model.addAttribute("secao", noticiaService.get(id_noticia).getSecao());
		model.addAttribute("comentario", new Comentario());
		model.addAttribute("listacomentarios", comentarioService
				.getComentarios(noticiaService.get(id_noticia)));
		
		if(session.getAttribute("PESSOA_LOGADA")  != null){
			Pessoa pessoa = (Pessoa) session.getAttribute("PESSOA_LOGADA");
			if(pessoa.isLeitor()){
				model.addAttribute("pessoa", pessoa);
			}
		}
		return TEMPLATE_NOTICIA;
	}

}
