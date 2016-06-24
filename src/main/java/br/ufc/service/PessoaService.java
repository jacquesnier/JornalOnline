package br.ufc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.model.Pessoa;
import br.ufc.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public void addOrUpdate(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
	}

	public void delete(Long id) {
		if (pessoaRepository.findOne(id) != null)
			pessoaRepository.delete(id);
	}

	public Pessoa get(Long id) {
		return pessoaRepository.findOne(id);
	}
	
	public void login (Pessoa pessoa){
		
	}

}
