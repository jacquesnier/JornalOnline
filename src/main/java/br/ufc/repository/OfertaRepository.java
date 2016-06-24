package br.ufc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.model.Classificado;
import br.ufc.model.Oferta;

@Repository
public interface OfertaRepository extends CrudRepository<Oferta, Long> {
	public void addClassificado (Classificado classificado, Oferta oferta);
	public Oferta findOfertaByClassificacao (Classificado classificado);
}
