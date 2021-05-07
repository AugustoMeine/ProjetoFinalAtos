package br.apm.SpringAPI.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Mensagem {
	@ApiModelProperty(notes = "ID da mensagem", name="idMensagem", required=true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idMensagem;
	
	@ApiModelProperty(notes = "ID do Usuário Remetente", name="idUsuarioRemetente",required=true, value="idUsuarioRemetente")
	@Column(nullable = false)
	private long idUsuarioRemetente;
	
	@ApiModelProperty(notes = "ID do Usuário Destinatário", name="idUsuarioDestinatario",required=true, value="idUsuarioDestinatario")
	@Column(nullable = false)
	private long idUsuarioDestinatario;
	
	@ApiModelProperty(notes = "Data da mensagem", name="data",required=true, value="data")
	@Column(nullable = false)
	private String data;
	
	@ApiModelProperty(notes = "Hora da mensagem", name="hora",required=true, value="hora")
	@Column(nullable = false)
	private String hora;
	
	@ApiModelProperty(notes = "A mensagem", name="mensagem",required=true, value="mensagem")
	@Column(nullable = false)
	private String mensagem;

	public long getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(long idMensagem) {
		this.idMensagem = idMensagem;
	}

	public long getIdUsuarioRemetente() {
		return idUsuarioRemetente;
	}

	public void setIdUsuarioRemetente(long idUsuarioRemetente) {
		this.idUsuarioRemetente = idUsuarioRemetente;
	}

	public long getIdUsuarioDestinatario() {
		return idUsuarioDestinatario;
	}

	public void setIdUsuarioDestinatario(long idUsuarioDestinatario) {
		this.idUsuarioDestinatario = idUsuarioDestinatario;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idMensagem ^ (idMensagem >>> 32));
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
		Mensagem other = (Mensagem) obj;
		if (idMensagem != other.idMensagem)
			return false;
		return true;
	}
	
	
}
