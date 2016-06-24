package br.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.model.Comentario;
import br.ufc.model.Secao;
import br.ufc.model.Noticia;
import br.ufc.repository.NoticiaRepository;

@Service
public class NoticiaService {

	@Autowired
	private NoticiaRepository noticiaRepository;
	
	public void addOrUpdate(Noticia noticia) {
		noticiaRepository.save(noticia);
	}

	public List<Noticia> list() {
		return (List<Noticia>) noticiaRepository.findAll();
	}

	public void delete(Long id) {
		if (noticiaRepository.findOne(id) != null)
			noticiaRepository.delete(id);
	}

	public Noticia get(Long id) {
		return noticiaRepository.findOne(id);
	}

	public List<Noticia> getByNome(String nome) {
		return noticiaRepository.findNoticiaByNome(nome);
	}

	public List<Noticia> getBySecao(Secao secao) {
		return noticiaRepository.findNoticiaBySecao(secao);
	}
	
	public void addComentario (Comentario comentario, Noticia noticia){
		noticia.getComentarios().add(comentario);
		noticiaRepository.save(noticia);
	}
}
