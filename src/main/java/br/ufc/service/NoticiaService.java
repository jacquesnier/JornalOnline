package br.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.model.Noticia;
import br.ufc.model.Pessoa;
import br.ufc.model.Secao;
import br.ufc.repository.NoticiaRepository;

@Service
public class NoticiaService {

	@Autowired
	NoticiaRepository noticiaRepository;
	
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
	
	public List<Noticia> getNoticiasBySecao(Secao secao){
		return noticiaRepository.findNoticiaBySecao(secao);
	}
	
	public List<Noticia> getNoticiasByJornalista(Pessoa pessoa){
		return noticiaRepository.findNoticiaByAutor(pessoa);
	}

}
