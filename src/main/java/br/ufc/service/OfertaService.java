package br.ufc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.model.Oferta;
import br.ufc.repository.OfertaRepository;

@Service
public class OfertaService {
	@Autowired
	OfertaRepository ofertaRepository;
	
	public void addOrUpdate(Oferta oferta) {
		ofertaRepository.save(oferta);
	}
}
