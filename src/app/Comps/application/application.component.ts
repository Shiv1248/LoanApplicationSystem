import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatStepperModule} from '@angular/material/stepper';
import {MatButtonModule} from '@angular/material/button';
import { NavbarComponent } from '../navbar/navbar.component';
import {NgFor} from '@angular/common';
import {MatSelectModule} from '@angular/material/select';
import {MatIconModule} from '@angular/material/icon';
import { DocumentTypes, LoanApplications } from 'src/app/LoanApplications';
import { GetDocService } from 'src/app/services/get-doc.service';
import { GetLoanService } from 'src/app/services/get-loan.service';
import { ActivatedRoute, Router } from '@angular/router';


interface doc {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-application',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.css'],
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
export class ApplicationComponent implements OnInit {
  appId:number;
  constructor(private _formBuilder: FormBuilder, private getDoc:GetDocService, private service:GetLoanService, private route: ActivatedRoute, private router:Router) {
  }
  hide = true;
  
  loanApp:LoanApplications=new LoanApplications();

  ngOnInit(): void {
    this.appId = this.route.snapshot.params['appId'];
    this.getApplication()
}

private getApplication(): any {
    
    this.service.getLoanApplicationBy(this.appId).subscribe((data:any)=>{
      this.loanApp=data;
      console.log(this.loanApp);
  });
  }

  

  back()
  {
    window.history.back();
  }
}



