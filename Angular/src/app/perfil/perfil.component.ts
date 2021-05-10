import { Usuario } from './../models/Usuario.model';
import { UsuarioService } from './../services/usuario.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
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
  atualizando: boolean = false
  primeiroBotao: string = "ATUALIZAR"
  segundoBotao: string = "DELETAR"

  constructor(private usuarioServico:UsuarioService, router:Router) { 
    this.router = router
  }

  ngOnInit(): void {
  }

  adicionaUsuario(){
    if((!this.nome) || (!this.email) || (!this.senha)){
      console.log("Dados incompletos!!!")
    }
    else{
      this.usuarioServico.adicionaUsuario(this.nome,this.email,this.senha).subscribe(
        (data: Usuario) =>{ 
          console.log("Cadastrado!!!")
          this.router.navigate([''])       
        },
        (error: any) =>{
          this.erro = error
          console.log("Erro >> " + this.erro)
        }
      )
    }    
  }

  primeiroBotaoFuncao(){
    if(this.atualizando){//CONFIRMAR
      //atualiza o usuário
      this.primeiroBotao = "ATUALIZAR"
      this.segundoBotao = "DELETAR"
      this.atualizando = false
    }
    else{//ATUALIZAR
      this.primeiroBotao = "CONFIRMAR"
      this.segundoBotao = "CANCELAR"
      this.atualizando = true
    }
  }

  segundoBotaoFuncao(){
    if(this.atualizando){//CANCELAR
      this.primeiroBotao = "ATUALIZAR"
      this.segundoBotao = "DELETAR"
      this.atualizando = false
    }
    else{//DELETAR
      //deletar usuário
      this.router.navigate([''])
    }
  }
}
