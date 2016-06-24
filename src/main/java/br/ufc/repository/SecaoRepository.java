package br.ufc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.model.Secao;

@Repository
public interface SecaoRepository extends CrudRepository<Secao, Long> {

}
