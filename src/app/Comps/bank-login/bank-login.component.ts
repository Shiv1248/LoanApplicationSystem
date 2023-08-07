import { Component } from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { NavbarComponent } from '../navbar/navbar.component';
import {MatCardModule} from '@angular/material/card';
import {FormBuilder, Validators, FormsModule, ReactiveFormsModule, FormGroup} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatStepperModule} from '@angular/material/stepper';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
@Component({
  selector: 'app-bank-login',
  templateUrl: './bank-login.component.html',
  styleUrls: ['./bank-login.component.css'],
  standalone: true,
  imports: [
    MatToolbarModule, 
    MatButtonModule, 
    MatIconModule,
    MatCardModule,
    NavbarComponent,
    MatFormFieldModule,
    MatStepperModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule
  ],
})
export class BankLoginComponent {
  firstFormGroup:FormGroup;
  hide=true;
  public user={
    email:'',
    password:'',
  }

  constructor(private _formBuilder: FormBuilder, private router:Router, private loginService:LoginService) {}

  ngOnInit()
  {
    this.firstFormGroup = this._formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
        password: [
          '',
        ],
    });
  }
  get email() {
    return this.firstFormGroup.get('email');
  }

  get password() {
    return this.firstFormGroup.get('password');
  }

  login(){
    this.user.email = this.firstFormGroup.value.email;
    this.user.password = this.firstFormGroup.value.password;
    this.loginService.bankLogin(this.user).subscribe(
      (response: any) => {
        console.log(response);
        if (response && response.jwtToken ) {
          localStorage.setItem('token', response.jwtToken);
          localStorage.setItem('email', response.username);
          this.loginService.findUserByEmail(localStorage.getItem('email')).subscribe((data:any)=>{
            console.log('data'+data.roles[0].name);
            if(data.roles[0].name=='Bank Manager')
            {
              alert('Login Successful');
              this.router.navigate(['/bank-home']);
            }
            else{
            alert("You are not a Bank Manager");
            }
          });
        } else {
          alert('Login failed');
        }
      },
      (error) => {
        console.log(error);
        alert('Something went wrong');
      }
    );
    console.log(this.firstFormGroup.value);
  }
}