package br.apm.SpringAPI.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Usuario {
	
	@ApiModelProperty(notes = "ID do Usuário", name="idUsuario", required=true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuario;
	
	@ApiModelProperty(notes = "Nome do Usuário", name="nome",required=true, value="nome")
	@Column(nullable = false)
	private String nome;
	
	@ApiModelProperty(notes = "E-mail do Usuário", name="email",required=true, value="email")
	@Column(nullable = false)
	private String email;
	
	@ApiModelProperty(notes = "Senha do Usuário", name="senha",required=true, value="senha")
	@Column(nullable = false)
	private String senha;
	
	@ApiModelProperty(notes = "Papel do Usuário", name="papel",required=true, value="papel")
	@Column(nullable = false)
	private String papel;
	
	@ApiModelProperty(notes = "Verificação do login do Usuário", name="logado",required=true, value="logado")
	@Column(nullable = false)
	private boolean logado;

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idUsuario ^ (idUsuario >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (idUsuario != other.idUsuario)
			return false;
		return true;
	}
		
}
