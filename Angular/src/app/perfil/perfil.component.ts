import { UsuarioLogadoService } from './../services/usuario-logado.service';
import { Usuario } from './../models/Usuario.model';
import { UsuarioService } from './../services/usuario.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {
  router: Router
  erro: any

  nome: string
  email: string
  senha: string
  listaUsuario: Usuario[]
  usuarioLogado: Usuario

  atualizando: boolean = false
  primeiroBotao: string = "ATUALIZAR"
  segundoBotao: string = "DELETAR"

  constructor(private usuarioLogadoServico: UsuarioLogadoService, private usuarioServico:UsuarioService, router:Router) { 
    this.router = router
  }

  ngOnInit(): void {
    //Se não existir usuário logado ele direciona para o login
    this.usuarioLogado = this.usuarioLogadoServico.getUsuarioLogado()
    if(this.usuarioLogado){
      this.nome = this.usuarioLogado.nome
      this.email = this.usuarioLogado.email
      this.senha = this.usuarioLogado.senha
    }
    else{
      this.router.navigate([''])
    }
    
  }

  primeiroBotaoFuncao(){
    if(this.atualizando){//CONFIRMAR
      //atualiza o usuário
      if((!this.nome) || (!this.email) || (!this.senha)){
        console.log("Dados incompletos!!!")
      }
      else{
        //atualiza
        this.usuarioServico.atualizaUsuario(this.usuarioLogado.idUsuario, this.nome, this.email, this.senha).subscribe(
          (data:Usuario)=>{
            console.log("Data >> " + data)
          },
          (error: any)=>{
            this.erro = error
            console.log("Erro >> " + this.erro)
          }
        )
        //Salva as informações do usuário no usuarioLogadoService
        this.usuarioLogado.nome = this.nome
        this.usuarioLogado.email = this.email
        this.usuarioLogado.senha = this.senha
        this.usuarioLogadoServico.setUsuarioLogado(this.usuarioLogado)
        
        //Modifica os botões
        this.primeiroBotao = "ATUALIZAR"
        this.segundoBotao = "DELETAR"
        this.atualizando = false
      }
    }
    else{//ATUALIZAR
      //Modifica os botões
      this.primeiroBotao = "CONFIRMAR"
      this.segundoBotao = "CANCELAR"
      this.atualizando = true
    }
  }

  segundoBotaoFuncao(){
    if(this.atualizando){//CANCELAR
      //Modifica os botões
      this.primeiroBotao = "ATUALIZAR"
      this.segundoBotao = "DELETAR"
      this.atualizando = false
    }
    else{//DELETAR
      //Verifica qual é o usuário logado e deleta
      if((!this.nome) || (!this.email) || (!this.senha)){
        console.log("Dados incompletos!!!")
      }
      else{
        this.usuarioServico.deletaUsuario(this.usuarioLogado.idUsuario).subscribe(
          (data: string)=>{
            console.log("Data >> " + data)
          },
          (error: any)=>{
            this.erro = error
            console.log("Erro >> " + this.erro)
          }
        )
        console.log("Usuário deletado!!!")
        //Envia para a tela de login
        this.router.navigate([''])
      }
    }
  }

  deslogar(){
    this.usuarioServico.deslogaUsuario(this.usuarioLogado.idUsuario).subscribe(
      (data: Usuario)=>{
        console.log("Data >> " + data)
        this.router.navigate([''])
      },
      (error: any)=>{
        this.erro = error
        console.log("Erro >> " + error)
      }
    )
  }

}
