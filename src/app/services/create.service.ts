import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoanApplications } from '../LoanApplications';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreateService {

  constructor(private http: HttpClient) { 
  
  }

  public createApp(loanApp:LoanApplications): Observable<Object>
  {
    return this.http.post('http://localhost:9090/api/loanapplication', loanApp);
  }


  public loanApplicationResponse(loanApplicationId: number, loanApplication: any){
    return this.http.put("http://localhost:9090/api/loanapplication/" +loanApplicationId, loanApplication);
  }
}
