package br.ufc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.model.Categoria;
import br.ufc.model.Noticia;

@Repository
public interface NoticiaRepository extends CrudRepository<Noticia, Integer> {
	public List<Noticia> findNoticiaByCategoria(Categoria categoria);
	public List<Noticia> findNoticiaByNome(String nome);
}
