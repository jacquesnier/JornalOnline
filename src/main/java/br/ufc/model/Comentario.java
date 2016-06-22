package br.ufc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Comentario {
	
	@Id
	@Column(name = "comentario_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty
	@ManyToOne
	@JoinColumn(name="pessoa_id",
			referencedColumnName="pessoa_id")
	private Pessoa autor;
	
	@NotEmpty
	private String conteudo;
	
	@ManyToOne
	@JoinColumn(name="noticia_id")
	private Noticia noticia;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Pessoa getAutor() {
		return autor;
	}
	public void setAutor(Pessoa usuario) {
		this.autor = autor;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
}
