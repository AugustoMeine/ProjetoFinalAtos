import { BatePapoComponent } from './bate-papo/bate-papo.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { LoginComponent } from './login/login.component';
import { PerfilComponent } from './perfil/perfil.component';

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path:"",component:LoginComponent},
  {path:"Cadastro",component:CadastroComponent},
  {path:"Bate-papo",component:BatePapoComponent},
  {path:"Perfil",component:PerfilComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
