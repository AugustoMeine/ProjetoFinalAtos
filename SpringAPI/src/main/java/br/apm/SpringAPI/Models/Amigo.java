package br.apm.SpringAPI.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Amigo {
	
	@ApiModelProperty(notes = "idAmigo", name="idAmigo", required=true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAmigo;
	
	@ApiModelProperty(notes = "ID do usuário do perfil", name="idUsuarioPerfil",required=true, value="idUsuarioPerfil")
	@Column(nullable = false)
	private long idUsuarioPerfil;
	
	@ApiModelProperty(notes = "ID do usuário amigo", name="idUsuarioAmigo",required=true, value="idUsuarioAmigo")
	@Column(nullable = false)
	private long idUsuarioAmigo;

	public long getIdAmigo() {
		return idAmigo;
	}

	public void setIdAmigo(long idAmigo) {
		this.idAmigo = idAmigo;
	}

	public long getIdUsuarioPerfil() {
		return idUsuarioPerfil;
	}

	public void setIdUsuarioPerfil(long idUsuarioPerfil) {
		this.idUsuarioPerfil = idUsuarioPerfil;
	}

	public long getIdUsuarioAmigo() {
		return idUsuarioAmigo;
	}

	public void setIdUsuarioAmigo(long idUsuarioAmigo) {
		this.idUsuarioAmigo = idUsuarioAmigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idAmigo ^ (idAmigo >>> 32));
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
		Amigo other = (Amigo) obj;
		if (idAmigo != other.idAmigo)
			return false;
		return true;
	}
	
	
}
