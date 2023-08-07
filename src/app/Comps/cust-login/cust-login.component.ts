import {Component} from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatCardModule} from '@angular/material/card';
import { NavbarComponent } from '../navbar/navbar.component';
import {FormBuilder, Validators, FormsModule, ReactiveFormsModule, FormGroup} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatStepperModule} from '@angular/material/stepper';
import {MatButtonModule} from '@angular/material/button';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
@Component({
  selector: 'app-cust-login',
  templateUrl: './cust-login.component.html',
  styleUrls: ['./cust-login.component.css'],
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
    MatInputModule,
    MatSnackBarModule
  ],
})


export class CustLoginComponent {
  firstFormGroup:FormGroup;
  snackBarRef:any;
  condition: boolean=false;
  hide = true;
  public user={
    email:'',
    password:'',
  }
  constructor(private _formBuilder: FormBuilder, private router:Router, private snackBar: MatSnackBar, private loginService:LoginService) {}

  ngOnInit(){
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


  login()
  {
    this.user.email = this.firstFormGroup.value.email;
    this.user.password = this.firstFormGroup.value.password;
    this.loginService.custLogin(this.user).subscribe(
      (response: any) => {
        console.log(response);
        if (response && response.jwtToken) {
          alert('Login successful');
          localStorage.setItem('token', response.jwtToken);
          localStorage.setItem('email', response.username);
          this.router.navigate(['/cust-home',this.user.email]);
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

  signup(path: string)
  {
    this.router.navigate([path]);
  }
}