package br.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.PasswordEncryption;
import br.ufc.model.Pessoa;
import br.ufc.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	public void addOrUpdate(Pessoa pessoa) {
		pessoa.setSenha(PasswordEncryption.encrypt(pessoa.getSenha()));
		pessoaRepository.save(pessoa);
	}

	public List<Pessoa> list() {
		return (List<Pessoa>) pessoaRepository.findAll();
	}

	public void delete(Long id) {
		if (pessoaRepository.findOne(id) != null)
			pessoaRepository.delete(id);
	}

	public Pessoa get(Long id) {
		return pessoaRepository.findOne(id);
	}
	
	public Pessoa getPessoaByEmail(String email){
		return pessoaRepository.findPessoaByEmail(email);
	}
	
	public Pessoa getPessoaByLoginAndSenha(String login, String senha){
		return pessoaRepository.findPessoaByLoginAndSenha(login, senha);
	}
}
