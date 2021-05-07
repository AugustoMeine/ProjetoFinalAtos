package br.apm.SpringAPI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.apm.SpringAPI.Models.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long>{

}
