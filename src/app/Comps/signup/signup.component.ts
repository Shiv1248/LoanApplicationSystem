import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators, FormsModule, ReactiveFormsModule, FormGroup} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatStepperModule} from '@angular/material/stepper';
import {MatButtonModule} from '@angular/material/button';
import { NavbarComponent } from '../navbar/navbar.component';
import {NgFor} from '@angular/common';
import {MatSelectModule} from '@angular/material/select';
import {MatIconModule} from '@angular/material/icon';
import { Router } from '@angular/router';
import { RegistrationService } from 'src/app/services/registration.service';

interface doc {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
  standalone: true,
  imports: [
    MatButtonModule,
    MatStepperModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    NavbarComponent,
    NgFor,
    MatSelectModule,
    MatIconModule
  ],
})
export class SignupComponent implements OnInit {

  firstFormGroup: FormGroup;
  loading = false;
  submitted = false;

  credentials={
    name:'',
    email:'',
    password:'',
  }
  

  constructor(private formBuilder: FormBuilder, private router:Router, private registerService:RegistrationService) {}
  hide = true;
  ngOnInit(): void {
    this.firstFormGroup = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: [
        '',
        [Validators.required
        
        ],
      ],
    });
  }
  get name() {
    return this.firstFormGroup.get('name');
  }

  get email() {
    return this.firstFormGroup.get('email');
  }

  get password() {
    return this.firstFormGroup.get('password');
  }

  onSubmit(path: string)
  {
    this.submitted = true;
    this.loading = true;
    this.credentials.email=this.firstFormGroup.value.email;
    this.credentials.password=this.firstFormGroup.value.password;
    this.credentials.name=this.firstFormGroup.value.name;
    this.registerService.doRegister(this.credentials).subscribe(
      (response:any)=>{
        console.log(response);
        if(response.status==200){
          alert("Registration successful");
        }
      },
      (error)=>{
        console.log(error);
        alert("Something went wrong");
      }
    );
    console.log(this.firstFormGroup.value);
    this.router.navigate([path]);
  }
}
