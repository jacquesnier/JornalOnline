package br.ufc.repository;

import org.springframework.data.repository.CrudRepository;

import br.ufc.model.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

}
