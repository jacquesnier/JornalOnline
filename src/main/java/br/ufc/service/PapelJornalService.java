package br.ufc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.model.Papel;
import br.ufc.model.PapelJornal;
import br.ufc.model.Pessoa;
import br.ufc.repository.PapelJornalRepository;

@Service
public class PapelJornalService {
	
	@Autowired
	PapelJornalRepository pJRepository;
	
	public void addOrUpdate(Pessoa pessoa, Papel papel) {
		PapelJornal papelJornal = new PapelJornal();
		papelJornal.setPapel(papel);
		papelJornal.setPessoa(pessoa);
		pJRepository.save(papelJornal);
	}

}
