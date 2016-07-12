package br.ufc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.model.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long>{
	public Pessoa findPessoaByEmail (String email);
	public Pessoa findPessoaByLoginAndSenha(String login, String senha);
}
