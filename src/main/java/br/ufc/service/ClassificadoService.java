package br.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.model.Classificado;
import br.ufc.repository.ClassificadoRepository;

@Service
public class ClassificadoService {
	
	@Autowired
	ClassificadoRepository classificadoRepository;
		
	public void addOrUpdate(Classificado classificado) {
		classificadoRepository.save(classificado);
	}

	public List<Classificado> list() {
		return (List<Classificado>) classificadoRepository.findAll();
	}

	public Classificado get(Long id) {
		return classificadoRepository.findOne(id);
	}

}