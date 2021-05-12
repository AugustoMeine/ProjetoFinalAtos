package br.apm.SpringAPI.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.apm.SpringAPI.Models.Amigo;
import br.apm.SpringAPI.Models.Usuario;
import br.apm.SpringAPI.Repositories.AmigoRepository;
import br.apm.SpringAPI.Repositories.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@Api(value = "Swagger2RestController",description = "API para o gerenciamento de amigos")
@RestController
@RequestMapping("/Amigo")
public class AmigoController {
	
	@Autowired
	private AmigoRepository aRepository;
	
	@Autowired
	private UsuarioRepository uRepository;
	
	@ApiOperation(value = "Retorna a lista dos amigos", response = Iterable.class, tags = "getAmigos")
	@GetMapping
	public List<Amigo> getAmigos(){
		return(aRepository.findAll());
	}
	
	@ApiOperation(value = "Retorna a lista de amigos com o idUsuarioPerfil como parâmetro", response = Iterable.class, tags = "getAmigo")
	@GetMapping("/{idUsuarioPerfil}")
	public List<Amigo> getAmigo(@PathVariable long idUsuarioPerfil){
		return(aRepository.findByIdUsuarioPerfil(idUsuarioPerfil));
	}
	
	@ApiOperation(value = "Adiciona um amigo", response = Iterable.class, tags = "adicionaAmigo")
	@GetMapping("/Adicionar/{idUsuarioPerfil}/{idUsuarioAmigo}")
	public Amigo adicionaAmigo(@PathVariable long idUsuarioPerfil , @PathVariable long idUsuarioAmigo){
		List<Usuario> listaUsuario = uRepository.findAll();
		List<Amigo> listaAmigo = aRepository.findByIdUsuarioPerfil(idUsuarioPerfil);
		Amigo novoAmigo = new Amigo();
		boolean perfil = false;
		boolean amigo = false;
		
		//Verifica se o usuário perfil é o mesmo usuário amigo
		if(idUsuarioAmigo == idUsuarioPerfil) {
			System.out.println("1");
			return(null);
		}
		
		//Verifica se o usuário perfil e o usuário amigo existem
		for(Usuario auxUsuario : listaUsuario) {
			if(auxUsuario.getIdUsuario() == idUsuarioPerfil) {
				perfil = true;
			}
			if(auxUsuario.getIdUsuario() == idUsuarioAmigo) {				
				amigo = true;
			}
		}
		if((perfil == false) || (amigo == false)){
			System.out.println("2");
			return(null);
		}
		
		//Verifica se o usuário amigo já foi adicionado
		for(Amigo auxAmigo: listaAmigo) {
			if(auxAmigo.getIdUsuarioAmigo() == idUsuarioAmigo) {
				System.out.println("3");
				return(null);
			}
		}
		
		//Adiciona as informações
		novoAmigo.setIdUsuarioPerfil(idUsuarioPerfil);
		novoAmigo.setIdUsuarioAmigo(idUsuarioAmigo);

		aRepository.save(novoAmigo);
		
		return(novoAmigo);
	}
	
	@ApiOperation(value = "Deleta um amigo", response = Iterable.class, tags = "deletaAmigo")
	@GetMapping("/Deletar/{idUsuarioPerfil}/{idUsuarioAmigo}")
	public String deletaAmigo(@PathVariable long idUsuarioPerfil , @PathVariable long idUsuarioAmigo){
		List<Amigo> listaAmigo = aRepository.findByIdUsuarioPerfil(idUsuarioPerfil);
		//Verifica se o amigo existe
		for(Amigo auxAmigo: listaAmigo) {
			if(auxAmigo.getIdUsuarioAmigo() == idUsuarioAmigo) {
				aRepository.delete(auxAmigo);
				return("Amigo deletado!!!");
			}
		}
		
		return("Amigo não encontrado!!!");
	}
}
