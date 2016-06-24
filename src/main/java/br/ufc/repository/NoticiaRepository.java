package br.ufc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.model.Noticia;
import br.ufc.model.Secao;

@Repository
public interface NoticiaRepository extends CrudRepository<Noticia, Long> {
	public List<Noticia> findNoticiaBySecao(Secao secao);
	public List<Noticia> findNoticiaByNome(String nome);
}
