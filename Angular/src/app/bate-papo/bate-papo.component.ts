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
  
  listaAmigo: Amigo[]
  listaUsuario: Usuario[]
  listaMensagem: Mensagem[]

  listaAmigosAdicionados: string[]
  nomeUsuarioGerenciamento: string
  mensagemTextArea: string

  constructor() { 
  }

  ngOnInit(): void {
  }

}
