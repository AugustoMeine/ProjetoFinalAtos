import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AmigoService {

  constructor(private http:HttpClient) { 

  }

  public getAmigos(): Observable<any>{
    return(this.http.get('http://localhost:8080/Amigo'))
  }

  public getAmigo(idUsuarioPerfil: number): Observable<any>{
    return(this.http.get('http://localhost:8080/Amigo/' + idUsuarioPerfil))
  }

  public adicionaAmigo(idUsuarioPerfil: number, idUsuarioAmigo: number): Observable<any>{
    return(this.http.get('http://localhost:8080/Amigo/Adicionar/' + idUsuarioPerfil + '/' + idUsuarioAmigo))
  }

  public deletaAmigo(idUsuarioPerfil: number, idUsuarioAmigo: number): Observable<any>{
    return(this.http.get('http://localhost:8080/Amigo/Deletar/' + idUsuarioPerfil + '/' + idUsuarioAmigo ))
  }
}
