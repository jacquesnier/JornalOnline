package br.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.model.Secao;
import br.ufc.model.Noticia;
import br.ufc.repository.NoticiaRepository;

@Service
public class NoticiaService {

	@Autowired
	private NoticiaRepository JornalRepository;
	
	public void addOrUpdate(Noticia noticia) {
		JornalRepository.save(noticia);
	}

	public List<Noticia> list() {
		return (List<Noticia>) JornalRepository.findAll();
	}

	public void delete(Integer id) {
		if (JornalRepository.findOne(id) != null)
			JornalRepository.delete(id);
	}

	public Noticia get(Integer id) {
		return JornalRepository.findOne(id);
	}

	public List<Noticia> getByName(String nome) {
		return JornalRepository.findNoticiaByNome(nome);
	}

	public List<Noticia> getByCategoria(Secao categoria) {
		return JornalRepository.findNoticiaByCategoria(categoria);
	}
}
