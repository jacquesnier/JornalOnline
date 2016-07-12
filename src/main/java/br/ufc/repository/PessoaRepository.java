package br.ufc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.model.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long>{
	public Pessoa findPessoaByLogin (String login);
	public Pessoa findPessoaByLoginAndSenha(String login, String senha);
}
