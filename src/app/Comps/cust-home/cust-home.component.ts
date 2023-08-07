import {Component, OnInit} from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {NavbarComponent} from '../navbar/navbar.component';
import {MatCardModule} from '@angular/material/card';
import { ActivatedRoute, Router } from '@angular/router';
import { LoanApplications } from 'src/app/LoanApplications';
import { GetLoanService } from 'src/app/services/get-loan.service';
import { CommonModule, NgFor } from '@angular/common';
import { MatTabsModule } from '@angular/material/tabs';
import { LoginService } from 'src/app/services/login.service';
import { Observable } from 'rxjs';
import { FormGroup } from '@angular/forms';
@Component({
  selector: 'app-cust-home',
  templateUrl: './cust-home.component.html',
  styleUrls: ['./cust-home.component.css'],
  standalone: true,
  imports: [MatToolbarModule, MatButtonModule, MatIconModule, NavbarComponent, MatCardModule,
  NgFor, CommonModule, MatTabsModule],
})
export class CustHomeComponent implements OnInit{

  applicantEmail:string='';
  int:string='';
  isLoggedIn$: Observable<boolean>; 
  
  loanApp:LoanApplications=new LoanApplications();
  loanApplications:LoanApplications[]=[];
  constructor(private router:Router, private service:GetLoanService, private loginService: LoginService, private route: ActivatedRoute){}

  ngOnInit(): void {
    this.applicantEmail = this.route.snapshot.params['email'];
    this.getApplication()
}

private getApplication(): any {
    
    this.service.getApplicationByEmail(this.applicantEmail).subscribe((data:any)=>{
      this.loanApplications=data;
      console.log(this.loanApp);
  });
  }

  /** 
  find():void{
    let num: number = parseInt(this.int);
    this.service.getLoanApplicationBy(num).subscribe(data =>{
      this.loanApplications=data as [];
      console.log(data);
    });
    }
    */

  add(path:string)
  {
    this.router.navigate([path]);
  }

  view(appId: number){
    
    this.router.navigate(['/application', appId]);
  }

  
}