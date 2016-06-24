package br.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.model.Classificado;
import br.ufc.model.Oferta;
import br.ufc.repository.OfertaRepository;

@Service
public class OfertaService {
	
	@Autowired
	private OfertaRepository ofertaRepository;
	
	public void addOrUpdate(Oferta oferta) {
		float valor = ofertaRepository.findOfertaByClassificacao(oferta.getClassificado()).getValor();
		if(oferta.getValor() > valor){
		ofertaRepository.save(oferta);
		}
	}

	public List<Oferta> list() {
		return (List<Oferta>) ofertaRepository.findAll();
	}

	public void delete(Long id) {
		if (ofertaRepository.findOne(id) != null)
			ofertaRepository.delete(id);
	}

	public Oferta get(Long id) {
		return ofertaRepository.findOne(id);
	}
	
	public void addClassificado (Classificado classificado, Oferta oferta){
		oferta.setClassificado(classificado);
		ofertaRepository.save(oferta);
	}
}
