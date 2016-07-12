package br.ufc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.model.Oferta;

@Repository
public interface OfertaRepository extends CrudRepository<Oferta, Long> {

}
