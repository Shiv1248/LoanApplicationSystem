import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class GetLoanService {
  private apiUrl = 'http://localhost:9090/api/loanapplications';

  constructor(private http: HttpClient) { }

  getAllLoanApplications(){
    return this.http.get(this.apiUrl);
  }

  getApplicationByEmail(applicantEmail: string){
    return this.http.get('http://localhost:9090/api/loanapplication/'+applicantEmail);
  }
  getNewLoanApplications(){
    return this.http.get(`${this.apiUrl}/new`);
  }

  getApprovedLoanApplications(){
    return this.http.get(`${this.apiUrl}/approved`);
  }

  getRejectedLoanApplications(){
    return this.http.get(`${this.apiUrl}/rejected`);
  }

  getLoanApplicationBy(loanApplicationId: number) {
    return this.http.get(`${this.apiUrl}/${loanApplicationId}`);
  }


  // Add other methods for interacting with the backend API as needed
}
