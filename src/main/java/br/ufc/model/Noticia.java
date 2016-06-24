package br.ufc.model;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Noticia {
	
	@Id
	@Column(name = "noticia_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty
	private String titulo;
	
	@NotEmpty
	private Date data;
	
	@NotEmpty
	private String subtitulo;
	
	@NotEmpty
	private String conteudo;
	
	@NotEmpty
	@ManyToOne
	@JoinColumn(name="pessoa_id",
			referencedColumnName="pessoa_id")
	private Pessoa autor;
	
	@NotEmpty
	@OneToOne
	@JoinColumn(name="secao_id",
			referencedColumnName="secao_id")
	private Secao secao;
	
	@OneToMany(mappedBy = "noticia")
	private List<Comentario> comentarios;
	
	private String foto;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getSubtitulo() {
		return subtitulo;
	}
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public Pessoa getAutor() {
		return autor;
	}
	public void setAutor(Pessoa autor) {
		this.autor = autor;
	}
	
	public Secao getSecao() {
		return secao;
	}
	public void setSecao(Secao secao) {
		this.secao = secao;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentariosNoticia(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

}