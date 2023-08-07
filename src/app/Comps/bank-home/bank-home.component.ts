import { Component, Input, OnInit } from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { NavbarComponent } from '../navbar/navbar.component';
import {MatCardModule} from '@angular/material/card';
import {MatTabsModule} from '@angular/material/tabs';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import { ActivatedRoute, Router } from '@angular/router';
import { LoanApplications } from 'src/app/LoanApplications';
import { GetLoanService } from 'src/app/services/get-loan.service';
import { DatePipe, NgFor, NgIf, formatDate } from '@angular/common';
import {
  MatBottomSheet,
  MatBottomSheetModule,
  MatBottomSheetRef,
} from '@angular/material/bottom-sheet';
import { CreateService } from 'src/app/services/create.service';

@Component({
  selector: 'app-bank-home',
  templateUrl: './bank-home.component.html',
  styleUrls: ['./bank-home.component.css'],
  standalone: true,
  imports: [MatToolbarModule, 
    MatButtonModule, 
    MatIconModule, 
    NavbarComponent,
    MatCardModule,
  MatTabsModule,
  MatTableModule,
  MatPaginatorModule,
  NgFor,
  NgIf,
  MatButtonModule, MatBottomSheetModule,
],
providers: [DatePipe]
})
export class BankHomeComponent implements OnInit {
  search: any;
  value:string='';
  isClicked=false;
  loanApplications:LoanApplications=new LoanApplications();
  loanNew:LoanApplications[]=[];
  loanApp:LoanApplications[]=[];
  loanRej: LoanApplications[]=[];
  constructor(private router:Router, private _bottomSheet: MatBottomSheet, private datePipe: DatePipe, private service:GetLoanService, private respond:CreateService, private route:ActivatedRoute){
    
  }

  ngOnInit(): void {
    this.service.getNewLoanApplications().subscribe(data=>{
      this.loanNew=data as [];
      console.log(data);
    });
    this.service.getApprovedLoanApplications().subscribe(data=>{
      this.loanApp=data as [];
      console.log(data);
    });
    this.service.getRejectedLoanApplications().subscribe(data=>{
      this.loanRej=data as [];
      console.log(data);
    });
    
  }
  Search()
  {
    this.value=this.search;
    this.service.getApplicationByEmail(this.value).subscribe((data:any)=>{
      this.loanApplications=data;
      console.log(this.value);
  });
  }
  view(loanId:number){
    this.router.navigate(['/application',loanId]);
  }
  approve(appId: number){
    this.loanApplications.applicationStatus='Approved';
    this.respond.loanApplicationResponse(appId,this.loanApplications).subscribe(data=>{
      console.log(data);
    });
    this.service.getNewLoanApplications().subscribe(data=>{
      this.loanNew=data as [];
      console.log(data);
    });
    this.service.getApprovedLoanApplications().subscribe(data=>{
      this.loanApp=data as [];
      console.log(data);
    });
    this.service.getRejectedLoanApplications().subscribe(data=>{
      this.loanRej=data as [];
      console.log(data);
    });
    this.router.navigate(['bank-home']);
  }
  reject(appId: number){
    this.loanApplications.applicationStatus='Rejected';
    this.router.navigate(['bank-home']);
    this.respond.loanApplicationResponse(appId,this.loanApplications).subscribe(data=>{
      console.log(data);
    });
    this.service.getNewLoanApplications().subscribe(data=>{
      this.loanNew=data as [];
      console.log(data);
    });
    this.service.getApprovedLoanApplications().subscribe(data=>{
      this.loanApp=data as [];
      console.log(data);
    });
    this.service.getRejectedLoanApplications().subscribe(data=>{
      this.loanRej=data as [];
      console.log(data);
    });
  }

}


