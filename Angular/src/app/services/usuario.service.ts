import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient) {

  }

  public getUsuarios():Observable<any>{
    return(this.http.get('http://localhost:8080/Usuario'))
  }

  public getUsuario(idUsuario:number):Observable<any>{
    return(this.http.get('http://localhost:8080/Usuario/'+ idUsuario))
  }

  public adicionaUsuario(nome: string, email: string, senha: string):Observable<any>{
    return(this.http.get('http://localhost:8080/Usuario/Adicionar/'+ nome + '/' + email + '/' + senha))
  }

  public atualizaUsuario(idUsuario: number, nome: string, login: string, senha: string):Observable<any>{
    return(this.http.get('http://localhost:8080/Usuario/Atualizar/' + idUsuario + '/' + nome + '/' + login + '/' + senha))
  }

  public deletaUsuario(idUsuario: number):Observable<any>{
    return(this.http.get('http://localhost:8080/Usuario/Deletar/'+ idUsuario))
  }

  
}
