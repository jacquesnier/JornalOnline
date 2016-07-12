package br.ufc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.model.Comentario;
import br.ufc.model.Noticia;

@Repository
public interface ComentarioRepository extends CrudRepository<Comentario, Long>{
	public List<Comentario> findComentarioByNoticia(Noticia noticia); 
}
