import { MensagemService } from './../services/mensagem.service';
import { Router } from '@angular/router';
import { UsuarioService } from './../services/usuario.service';
import { AmigoService } from './../services/amigo.service';
import { Mensagem } from './../models/Mensagem.model';
import { Usuario } from './../models/Usuario.model';
import { Amigo } from './../models/Amigo.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-bate-papo',
  templateUrl: './bate-papo.component.html',
  styleUrls: ['./bate-papo.component.css']
})
export class BatePapoComponent implements OnInit {
  router: Router
  erro: any

  listaAmigo: Amigo[]
  listaUsuario: Usuario[]
  listaMensagem: Mensagem[]
  usuarioLogado: Usuario

  nomeUsuarioGerenciamento: string
  mensagemTextArea: string

  constructor(private amigoServico: AmigoService, private usuarioServico: UsuarioService, private mensagemServico: MensagemService, router: Router) { 
    this.router = router

    // this.atualizarListaAmigos()
    // this.atualizarListaUsuario()
    // this.atualizarListaMensagem()
  }

  ngOnInit(): void {
  }

  atualizarListaAmigos(){
    this.amigoServico.getAmigo(this.usuarioLogado.idUsuario).subscribe(
      (data: Amigo[])=>{
        this.listaAmigo = data
        console.log("Data >> " + data)
      },
      (error: any)=>{
        this.erro = error
        console.log("Erro >> " + error)
      }
    )
  }

  atualizarListaUsuario(){
    this.usuarioServico.getUsuarios().subscribe(
      (data: Usuario[])=>{
        this.listaUsuario = data
        console.log("Data >> " + data)
      },
      (error: any)=>{
        this.erro = error 
        console.log("Erro >> " + error)
      }
    )
  }

  atualizarListaMensagem(){
    this.mensagemServico.getMensagens().subscribe(
      (data: Mensagem[])=>{
        this.listaMensagem = data
        console.log("Data >> " + data)
      },
      (error: any)=>{
        this.erro = error
        console.log("Erro >> " + error)
      }
    )
  }

  adicionarAmigo(){
    if(!this.nomeUsuarioGerenciamento){
      
    }
  }

  deletarAmigo(){

  }

}
