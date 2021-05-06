package br.apm.SpringAPI.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/Conversao")
public class ConversaoController {
	
	@GetMapping("/Converter/{texto}")
	public List<String> converterBinario(@PathVariable String texto){
		List<String> listaBinario = new ArrayList<String>();
		int i,letra;
		char c;
		
		//separa as letras para converter em binário
		for (i = 0; i< texto.length(); i++) {
		   c = texto.charAt(i);
		   letra =(int) c;//pega o seu valor representado na tabela ASCII
		   listaBinario.add(Integer.toBinaryString(letra) + " "); 
		}
		
		return(listaBinario);
	}
	
	@GetMapping("/Ler/{texto}")
	public List<Character> lerBinario(@PathVariable String texto){
		List<Character> listaCaracter = new ArrayList<Character>();
		
		String[] splitTexto = texto.split(" ");
		int i,j,count;
		
		for (i = 0; i < splitTexto.length ; i++) {
			count = 0;
			for(j = 0; j < splitTexto[i].length();j++) {
				if(splitTexto[i].charAt((splitTexto[i].length() - 1) - j) == 49) {
					count += Math.pow(2,j);
				}	
			}
			listaCaracter.add((char)count);			
		}
		
		return(listaCaracter);
	}
}
