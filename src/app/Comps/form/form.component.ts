import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild, ElementRef, Renderer2 } from '@angular/core';
import { LoanApplications, DocumentTypes } from 'src/app/LoanApplications';
import { CreateService } from 'src/app/services/create.service';
import { GetDocService } from 'src/app/services/get-doc.service';
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
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule, MatOption} from '@angular/material/core';

interface profession{
  value:string,
}

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
  standalone:true,
  imports:[
    CommonModule,
    MatButtonModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatSelectModule,
    MatStepperModule,
    NavbarComponent,
    NgFor,
    MatDatepickerModule,
    MatNativeDateModule,
     FormsModule,
     ReactiveFormsModule
  ]
})
export class FormComponent implements OnInit {
  email:string;
  app: LoanApplications=new LoanApplications();
  doc: DocumentTypes[]=[];
  sampleForm: FormGroup;
  
  constructor(private router:Router, private getDoc:GetDocService, private create:CreateService, fb: FormBuilder){
    this.sampleForm = fb.group({  
      'documentId': [null]   // will use the property in html page  
      }) 
  }

  ngOnInit(): void {
      this.getDocs();
      this.email=this.app.applicantEmail;
  }
  
  public prof:profession[] = [
    {value: 'Doctor'},
    {value: 'Engineer'},
    {value: 'Teacher'},
    {value: 'Farmer'},
    {value: 'Actor'},
    {value: 'Government Job'},
    {value: 'Private Job'},
    {value: 'Businessman'},
    {value: 'Other'},

];

  getDocs(): void{
    this.getDoc.getDocumentTypes().subscribe(data=>{
      this.doc=data;
      console.log(data);
    });
  }

  createApp(){
    this.create.createApp(this.app).subscribe(data =>{
      console.log(data);
      alert('Application Submitted Successfully.');
    });
  }
  submit(){
    this.createApp();
    this.router.navigate(['/cust-home', this.app.applicantEmail]);
  }
}