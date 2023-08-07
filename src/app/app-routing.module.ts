import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './Comps/home/home.component';
import { CustLoginComponent } from './Comps/cust-login/cust-login.component';
import { BankLoginComponent } from './Comps/bank-login/bank-login.component';
import { CustHomeComponent } from './Comps/cust-home/cust-home.component';
import { BankHomeComponent } from './Comps/bank-home/bank-home.component';
import { SignupComponent } from './Comps/signup/signup.component';
import { ApplicationComponent } from './Comps/application/application.component';
import { CommonModule } from '@angular/common';
import { FormComponent } from './Comps/form/form.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'cust-login', component: CustLoginComponent },
  { path: 'bank-login', component: BankLoginComponent },
  { path: 'cust-home/:email', component: CustHomeComponent },
  {
    path: 'bank-home', component:BankHomeComponent
  },
  { path: 'signup', component: SignupComponent },
  { path: 'application/:appId' , component:ApplicationComponent},
  { path: 'form' , component: FormComponent },
];

@NgModule({
  imports: [CommonModule ,RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
