package br.apm.SpringAPI.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.apm.SpringAPI.Models.Amigo;

@Repository
public interface AmigoRepository extends JpaRepository<Amigo, Long>{
	public List<Amigo> findByIdUsuarioPerfil(long idUsuarioPerfil); 
}
