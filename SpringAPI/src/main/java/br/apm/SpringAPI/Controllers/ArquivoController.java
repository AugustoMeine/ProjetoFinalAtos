package br.apm.SpringAPI.Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@Api(value = "Swagger2RestController",description = "API do controle do Usuario no banco de dados")
@RestController
@RequestMapping("/Arquivo")
public class ArquivoController {
	
	@ApiOperation(value = "Retorna que entrou na API com sucesso", response = Iterable.class, tags = "getArquivo")
	@GetMapping
	public String getArquivo(){
		return("REST API para Gerenciamento de arquivo .txt");
	}
	
	@ApiOperation(value = "Retorna ", response = Iterable.class, tags = "salvarArquivo")
	@GetMapping("Salvar/{path}/{texto}")
	public String getUsuario(@PathVariable String path, @PathVariable String texto) throws IOException{
		File arquivo = new File(path);
		
		//Verifica se o arquivo existe
		if(!arquivo.exists()) {
			FileWriter fw = new FileWriter(arquivo);
			BufferedWriter bw = new BufferedWriter(fw);
			
			//Escreve no arquivo
			bw.write(texto);
			
			//encerra o fluxo
			bw.close();
			fw.close();
			
			return("Arquivo salvo!!!");
		}else {
			return("Arquivo já existe!!!");
		}
		
	}
	
	@ApiOperation(value = "Retorna ", response = Iterable.class, tags = "leArquivo")
	@GetMapping("Ler/{path}")
	public String leArquivo(@PathVariable String path) throws IOException{
		File arquivo = new File(path);
		String texto = "";
		
		if(arquivo.exists()) {
			FileReader fr = new FileReader(arquivo);
			BufferedReader br = new BufferedReader(fr);
			
			while(br.ready()){		
				texto += br.readLine(); 
			}
			
			br.close();
			fr.close();
			return(texto);
		}else {
			return("Arquivo não existe!!!");
		}
		
	}
	
}
