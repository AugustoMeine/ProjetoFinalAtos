package br.apm.SpringAPI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.apm.SpringAPI.Models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}