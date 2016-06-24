package br.ufc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.model.Classificado;

@Repository
public interface ClassificadoRepository extends CrudRepository<Classificado, Long>{

}
