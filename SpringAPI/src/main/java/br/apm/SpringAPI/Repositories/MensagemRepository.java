package br.apm.SpringAPI.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.apm.SpringAPI.Models.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long>{
	public List<Mensagem> findByIdUsuarioRemetenteAndIdUsuarioDestinatario(long idUsuarioRemetente, long idUsuarioDestinatario);
}
