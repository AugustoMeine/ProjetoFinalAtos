import { Usuario } from './../models/Usuario.model';
import { UsuarioService } from './../services/usuario.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {
  router: Router
  erro: any

  nome: string
  email: string
  senha: string
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
}
