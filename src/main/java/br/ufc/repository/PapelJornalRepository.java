package br.ufc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.model.PapelJornal;

@Repository
public interface PapelJornalRepository extends CrudRepository<PapelJornal, Long> {

}
