package br.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufc.model.Secao;
import br.ufc.repository.SecaoRepository;

public class SecaoService {
	
	@Autowired
	private SecaoRepository secaoRepository;
	
	public void addOrUpdate(Secao secao) {
		secaoRepository.save(secao);
	}

	public List<Secao> list() {
		return (List<Secao>) secaoRepository.findAll();
	}

	public void delete(Long id) {
		if (secaoRepository.findOne(id) != null)
			secaoRepository.delete(id);
	}

	public Secao get(Long id) {
		return secaoRepository.findOne(id);
	}
}
