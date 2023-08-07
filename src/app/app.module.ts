import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormComponent } from './Comps/form/form.component';
import { NavbarComponent } from './Comps/navbar/navbar.component';
import { SignupComponent } from './Comps/signup/signup.component';
import { CustLoginComponent } from './Comps/cust-login/cust-login.component';
import { BankLoginComponent } from './Comps/bank-login/bank-login.component';
import { CustHomeComponent } from './Comps/cust-home/cust-home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BankHomeComponent } from './Comps/bank-home/bank-home.component';
import{HomeComponent} from './Comps/home/home.component';
import { ApplicationComponent } from './Comps/application/application.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
@NgModule({
  declarations: [
    AppComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    BankLoginComponent,
    CustHomeComponent,
    BankHomeComponent,
    NavbarComponent,
    HomeComponent,
    SignupComponent,
    CustLoginComponent,
    FormComponent,
    ApplicationComponent,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
