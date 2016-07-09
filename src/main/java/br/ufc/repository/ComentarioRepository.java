package br.ufc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.model.Comentario;

@Repository
public interface ComentarioRepository extends CrudRepository<Comentario, Long>{

}
