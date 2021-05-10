import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class MensagemService {

  constructor(private http: HttpClient) { 

  }
  public getMensagens(): Observable<any>{
    return(this.http.get('http://localhost:8080/Mensagem'))
  }

  public getMensagem(idMensagem: number):Observable<any>{
    return(this.http.get('http://localhost:8080/Mensagem/' + idMensagem))
  }

  public adicionaMensagem(idUsuarioRemetente: number, idUsuarioDestinatario: number, mensagem: string):Observable<any>{
    return(this.http.get('http://localhost:8080/Mensagem/Adicionar/' + idUsuarioRemetente + '/' + idUsuarioDestinatario + '/' + mensagem))
  }

  public deletaMensagem(idMensagem: number):Observable<any>{
    return(this.http.get('http://localhost:8080/Mensagem/Deletar/' + idMensagem))
  }
}
