package br.apm.SpringAPI.Controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.apm.SpringAPI.Models.Mensagem;
import br.apm.SpringAPI.Models.Usuario;
import br.apm.SpringAPI.Repositories.MensagemRepository;
import br.apm.SpringAPI.Repositories.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@Api(value = "Swagger2RestController",description = "API para o gerenciamento de mensagens")
@RestController
@RequestMapping("/Mensagem")
public class MensagemController {
	
	@Autowired
	private MensagemRepository mRepository;
	
	@Autowired
	private UsuarioRepository uRepository;
	
	@ApiOperation(value = "Retorna a lista das mensagens", response = Iterable.class, tags = "getMensagens")
	@GetMapping
	public List<Mensagem> getMensagens(){
		return(mRepository.findAll());
	}
	
	@ApiOperation(value = "Retorna a mensagem com o id como parâmetro", response = Iterable.class, tags = "getMensagem")
	@GetMapping("/{idMensagem}")
	public Optional<Mensagem> getMensagem(@PathVariable long idMensagem){
		return(mRepository.findById(idMensagem));
	}
	
	@ApiOperation(value = "Adiciona uma mensagem", response = Iterable.class, tags = "adicionaMensagem")
	@GetMapping("/Adicionar/{idUsuarioRemetente}/{idUsuarioDestinatario}/{mensagem}")
	public Mensagem adicionaMensagem(@PathVariable long idUsuarioRemetente, @PathVariable long idUsuarioDestinatario, @PathVariable String mensagem){
		
		Mensagem novaMensagem = new Mensagem();
		List<Usuario> listaUsuario = uRepository.findAll();
		Usuario usuarioDestinatario = uRepository.getOne(idUsuarioDestinatario);
		Usuario usuarioRemetente = uRepository.getOne(idUsuarioRemetente);
		boolean destinatario = false;
		boolean remetente = false;
		
		//Verifica se o usuário remetente e o usuário destinatário existem
		for(Usuario auxUsuario : listaUsuario) {
			if(auxUsuario.getIdUsuario() == idUsuarioDestinatario) {
				destinatario = true;
			}
			if(auxUsuario.getIdUsuario() == idUsuarioRemetente) {				
				remetente = true;
			}
		}
		if((destinatario == false) || (remetente == false)){
			return(null);
		}
		
		//Verifica se o usuário remetente é o mesmo usuário destinatário
		if(idUsuarioDestinatario == idUsuarioRemetente) {
			return(null);
		}
		
		//Verifica se a mensagem está vazia 
		if(mensagem.isBlank()) {
			return(null);
		}
		
		//Adiciona o usuário remetente
		novaMensagem.setIdUsuarioRemetente(usuarioRemetente.getIdUsuario());
		
		//Adiciona o usuário destinatário
		novaMensagem.setIdUsuarioDestinatario(usuarioDestinatario.getIdUsuario());
		
		//Adiciona a data e a hora 
		String aux = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		String[] dataHora = aux.split(" ");
		
		novaMensagem.setData(dataHora[0]); //salva dia/mês/ano
		novaMensagem.setHora(dataHora[1]); //salva hora:minuto:segundo
		
		//Adiciona a mensagem
		novaMensagem.setMensagem(mensagem);
		
		//Salva a mensagem
		mRepository.save(novaMensagem);
		
		return(novaMensagem);
	}
	
	@ApiOperation(value = "Deleta a mensagem com o id como parâmetro", response = Iterable.class, tags = "deletaMensagem")
	@GetMapping("/Deletar/{idMensagem}")
	public String deletaMensagem(@PathVariable long idMensagem){
		Mensagem mensagem = mRepository.getOne(idMensagem);
		
		if(mensagem == null) {
			return("Mensagem não existe!!!");
		}
		
		mRepository.deleteById(mensagem.getIdMensagem());
		return("Mensagem deletada!!!");
	}
	
}
