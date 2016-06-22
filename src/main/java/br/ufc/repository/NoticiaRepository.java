package br.ufc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.model.Secao;
import br.ufc.model.Noticia;

@Repository
public interface NoticiaRepository extends CrudRepository<Noticia, Integer> {
	public List<Noticia> findNoticiaByCategoria(Secao categoria);
	public List<Noticia> findNoticiaByNome(String nome);
}
