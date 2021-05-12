import { Usuario } from './../models/Usuario.model';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UsuarioLogadoService {
  usuarioLogado: Usuario

  constructor() { 
    this.usuarioLogado = null
  }

  setUsuarioLogado(auxUsuario: Usuario){
    this.usuarioLogado = auxUsuario
    console.log("Usuario >> " + this.usuarioLogado)
  }

  getUsuarioLogado(){
    return(this.usuarioLogado)
  }
}
