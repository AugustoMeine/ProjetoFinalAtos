package br.apm.SpringAPI.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.apm.SpringAPI.Models.Usuario;
import br.apm.SpringAPI.Repositories.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@Api(value = "Swagger2RestController",description = "API para gerenciamento dos usuários")
@RestController
@RequestMapping("/Usuario")
public class UsuarioController {
	@Autowired
	private UsuarioRepository uRepository;
	
	@ApiOperation(value = "Retorna a lista dos Usuários", response = Iterable.class, tags = "getUsuarios")
	@GetMapping
	public List<Usuario> getUsuarios(){
		return(uRepository.findAll());
	}
	
	@ApiOperation(value = "Retorna o Usuário com o id como parâmetro", response = Iterable.class, tags = "getUsuario")
	@GetMapping("/{idUsuario}")
	public Optional<Usuario> getUsuario(@PathVariable long idUsuario){
		return(uRepository.findById(idUsuario));
	}
		
	
	@ApiOperation(value = "Adiciona um Usuário", response = Iterable.class, tags = "adicionaUsuario")
	@GetMapping("/Adicionar/{nome}/{email}/{senha}")
	public String adicionaUsuario(@PathVariable String nome, @PathVariable String email, @PathVariable String senha){
		List<Usuario> listaUsuario = uRepository.findAll();
		Usuario novoUsuario = new Usuario();
		
		for(Usuario aux : listaUsuario) {
			if(aux.getNome().equals(nome) || aux.getEmail().equals(email)) {
				return("Usuário já existe!!!");
			}
		}
		
		//Adiciona o nome
		if(nome.isBlank()) {
			return("Nome inválido!!!");
		}
		else {
			novoUsuario.setNome(nome);
		}
		
		//Adiciona o e-mail
		if(email.isBlank()) {
			return("E-mail inválido!!!");
		}
		else {
			novoUsuario.setEmail(email);
		}
		
		//Adiciona a senha
		if(senha.isBlank()) {
			return("Senha inválido!!!");
		}
		else {
			novoUsuario.setSenha(senha);
		}
		
		//Adiciona o papel
		novoUsuario.setPapel("USER");
		
		//Seleciona como deslogado
		novoUsuario.setLogado(false);
		
		//salva o usuário
		uRepository.save(novoUsuario);		
		
		return("Usuário adicionado!!!");
	}
	
	@ApiOperation(value = "Atualiza o Usuário", response = Iterable.class, tags = "atualizaUsuario")
	@GetMapping("Atualizar/{idUsuario}/{nome}/{email}/{senha}")
	public String atualizaUsuario(@PathVariable long idUsuario, @PathVariable String nome, @PathVariable String email, @PathVariable String senha){
		List<Usuario> listaUsuario = uRepository.findAll();
		Usuario novoUsuario = new Usuario();

		//Verifica se o idUsuario existe
		for(Usuario auxId : listaUsuario) {
			if(auxId.getIdUsuario() == idUsuario) {
				
				for(Usuario aux : listaUsuario) {
					if(aux.getNome().equals(nome) || aux.getEmail().equals(email)) {
						return("Dados inválidos!!!");
					}
				}
				
				//Adiciona o idUsuario
				novoUsuario.setIdUsuario(idUsuario);
				
				//Adiciona o nome
				if(nome.isBlank()) {
					return("Nome inválido!!!");
				}
				else {
					novoUsuario.setNome(nome);
				}
				
				//Adiciona o e-mail
				if(email.isBlank()) {
					return("E-mail inválido!!!");
				}
				else {
					novoUsuario.setEmail(email);
				}
				
				//Adiciona a senha
				if(senha.isBlank()) {
					return("Senha inválida!!!");
				}
				else {
					novoUsuario.setSenha(senha);
				}
				
				//Adiciona o papel
				novoUsuario.setPapel("USER");
				
				//Seleciona como deslogado
				novoUsuario.setLogado(false);
				
				//salva o usuário
				uRepository.save(novoUsuario);		
				
				return("Usuário adicionado!!!");
			}
		}
		
		return("Usuário não encontrado!!!");			
	}
	
	@ApiOperation(value = "Deleta o Usuário com o id como parâmetro", response = Iterable.class, tags = "deletaUsuario")
	@GetMapping("/Deletar/{idUsuario}")
	public String deletaUsuario(@PathVariable long idUsuario){
		List<Usuario> listaUsuario = uRepository.findAll();
	
		//Verifica se o idUsuario existe
		for(Usuario auxId : listaUsuario) {
			if(auxId.getIdUsuario() == idUsuario) {
				uRepository.deleteById(idUsuario);
				return("Usuário deletado!!!");
			}
		}
		return("Usuário não encontrado!!!");
	}
}
