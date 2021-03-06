import { UsuarioLogadoService } from './../services/usuario-logado.service';
import { UsuarioService } from './../services/usuario.service';
import { Usuario } from './../models/Usuario.model';
import { Router } from '@angular/router';
import { Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  router: Router
  erro: any

  usuario: Usuario[]
  usuarioLogado: Usuario

  email: string
  senha: string

  constructor(private usuarioLogadoServico: UsuarioLogadoService, private usuarioServico:UsuarioService, router:Router) { 
    this.router = router
  }

  ngOnInit(): void {
    //Se não existir usuário logado ele direciona para o login
    this.usuarioLogado = this.usuarioLogadoServico.getUsuarioLogado()
    if(this.usuarioLogado){
      this.router.navigate(['Bate-papo'])
    }
  }

  verificarLogin(){
    this.usuarioServico.getUsuarios().subscribe(
      (data: Usuario[]) =>{
        this.usuario = data
        if(this.usuario){
          for(let aux of this.usuario){
            if((this.email === aux.email) && (this.senha === aux.senha)){
              this.usuarioServico.logaUsuario(aux.idUsuario).subscribe(
                (data: Usuario)=>{
                  console.log("Data >> " + data)
                  this.usuarioLogadoServico.setUsuarioLogado(data)
                  this.router.navigate(['Bate-papo'])
                },
                (error: any)=>{
                  this.erro = error
                  console.log("Erro >> " + error)
                }
              )
            }
          }
        }
      },
      (error: any) =>{
        this.erro = error
        console.log("Erro: " + this.erro)
      }
    )
  }
}
