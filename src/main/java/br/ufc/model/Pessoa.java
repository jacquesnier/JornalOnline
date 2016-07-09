package br.ufc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Pessoa {

	@Id
	@Column(name = "pessoa_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(mappedBy = "pessoa")
	private List<PapelJornal> papeis;

	@NotEmpty
	private String nome;

	@NotEmpty
	private String descricao;

	@NotEmpty
	@Column(unique = true)
	private String login;

	@NotEmpty
	private String senha;

	@NotEmpty
	@Column(unique = true)
	private String email;

	private String foto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<PapelJornal> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<PapelJornal> papeis) {
		this.papeis = papeis;
	}

	public boolean isLeitor() {
		for(PapelJornal papel:papeis){
			if(papel.getPapel() == Papel.LEITOR)
				return true;
		}
		return false;
	}

	public boolean isEditor() {
		for(PapelJornal papel:papeis){
			if(papel.getPapel() == Papel.EDITOR)
				return true;
		}
		return false;
	}

	public boolean isJornalista() {
		for(PapelJornal papel:papeis){
			if(papel.getPapel() == Papel.JORNALISTA)
				return true;
		}
		return false;
	}

}
