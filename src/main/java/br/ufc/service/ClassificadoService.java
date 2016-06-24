package br.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufc.model.Classificado;
import br.ufc.repository.ClassificadoRepository;

public class ClassificadoService {
	
	@Autowired
	private ClassificadoRepository classificadoRepository;
	
	public void addOrUpdate(Classificado classificado) {
		classificadoRepository.save(classificado);
	}

	public List<Classificado> list() {
		return (List<Classificado>) classificadoRepository.findAll();
	}

	public void delete(Long id) {
		if (classificadoRepository.findOne(id) != null)
			classificadoRepository.delete(id);
	}

	public Classificado get(Long id) {
		return classificadoRepository.findOne(id);
	}
}
