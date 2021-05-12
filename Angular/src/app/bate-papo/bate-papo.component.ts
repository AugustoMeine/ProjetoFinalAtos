import { UsuarioLogadoService } from './../services/usuario-logado.service';
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
  listaAmigosDoUsuario: Usuario[] 
  usuarioLogado: Usuario
  usuarioConversa: Usuario
  

  nomeUsuarioGerenciamento: string
  mensagemTextArea: string

  constructor(private usuarioLogadoServico: UsuarioLogadoService, private amigoServico: AmigoService, private usuarioServico: UsuarioService, private mensagemServico: MensagemService, router: Router) { 
    this.router = router
    this.listaAmigosDoUsuario = []
  }

  ngOnInit(): void {
    //Se não existir usuário logado ele direciona para o login
    this.usuarioLogado = this.usuarioLogadoServico.getUsuarioLogado()
    if(this.usuarioLogado){
      this.atualizarListaAmigos()
      this.atualizarListaUsuario()
      this.atualizarListaMensagem()
      this.atualizarListaAmigosDoUsuario()
    }
    else{
      this.router.navigate([''])
    }
    
  }

  atualizarListaAmigos(){
    this.amigoServico.getAmigo(this.usuarioLogado.idUsuario).subscribe(
      (data: Amigo[])=>{
        this.listaAmigo = data
        console.log("Lista de amigos atualizada >> " + data)
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
        console.log("Lista de usuários atualiza >> " + data)
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
        console.log("Lista de mensagens atualizada >> " + data)
      },
      (error: any)=>{
        this.erro = error
        console.log("Erro >> " + error)
      }
    )
  }

  atualizarListaAmigosDoUsuario(){
    this.amigoServico.getAmigo(this.usuarioLogado.idUsuario).subscribe(
      (data: Amigo[])=>{
        this.listaAmigosDoUsuario = []
        for(let amigo of data){
          this.usuarioServico.getUsuario(amigo.idUsuarioAmigo).subscribe(
            (data2: Usuario)=>{
              this.listaAmigosDoUsuario.push(data2)
            },
            (error: any)=>{
              this.erro = error
              console.log("Erro >> " + error)
            }
          )
        }
        console.log("Lista de amigos do usuário atualizada >> " + data)
        console.log(this.listaAmigosDoUsuario)
      },
      (error: any)=>{
        this.erro = error
        console.log("Erro >> " + error)
      }
    )
  }

  adicionarAmigo(){
    //Verifica se o nome existe
    if(!this.nomeUsuarioGerenciamento){
      console.log("Nome de usuário inválido!!!")
    }
    else{
      //Acha o ID do usuário que irá ser adicionado com base no nome digitado
      for(let aux of this.listaUsuario){
        if(aux.nome === this.nomeUsuarioGerenciamento){
          //Adiciona o amigo
          this.amigoServico.adicionaAmigo(this.usuarioLogado.idUsuario, aux.idUsuario).subscribe(
            (data: Amigo)=>{
              console.log("Amigo Adicionado!!! >> " + data)
              this.atualizarListaAmigos()
              this.atualizarListaAmigosDoUsuario()
            },
            (error: any)=>{
              this.erro = error
              console.log("Erro >> " + error)
            }
          )
        }
      }
    }
  }

  deletarAmigo(){
    //Verifica se o nome existe
    if(!this.nomeUsuarioGerenciamento){
      console.log("Nome de usuário inválido!!!")
    }
    else{
      //Acha o ID do usuário que irá ser adicionado com base no nome digitado
      for(let aux of this.listaUsuario){
        if(aux.nome === this.nomeUsuarioGerenciamento){
          //Adiciona o amigo
          this.amigoServico.deletaAmigo(this.usuarioLogado.idUsuario, aux.idUsuario).subscribe(
            (data: string)=>{
              console.log("Amigo deletado >> " + data)
            },
            (error: any)=>{
              this.erro = error
              console.log("Erro >> " + error)
            }
          )
        }
      }
      console.log("Usuário não existe!!!")
    }
  }

  amigoSelecionado(idUsuarioAmigo: number){
    console.log(idUsuarioAmigo)
  }

  enviarMensagem(){
    if(this.mensagemTextArea){
      this.mensagemServico.adicionaMensagem(this.usuarioLogado.idUsuario,this.usuarioConversa.idUsuario,this.mensagemTextArea).subscribe(
        (data: Mensagem)=>{
          console.log("Mensagem enviada >> " + data)
        },
        (error: any)=>{
          this.erro = error
          console.log("Erro >> " + error)
        }
      )
      this.mensagemTextArea = "";
    }
    else{
      console.log("Mensagem inválida!!!")
    }
  }

  limparMensagem(){
    this.mensagemTextArea = "";
  }
}
