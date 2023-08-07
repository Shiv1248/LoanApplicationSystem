import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  url="http://localhost:9090"
  constructor(private http:HttpClient) { }

  doRegister(credentials:any){
    return this.http.post(`${this.url}/auth/register`,credentials)
  }

  registerUser(token:any){
    localStorage.setItem("token",token)
    return true
  }

  isLoggedIn(){
    let token=localStorage.getItem("token")
    if(token==undefined || token==='' || token==null){
      return false
    }else{
      return true
    }
  }

  logout(){
    localStorage.removeItem("token")
    return true
  }

  getToken(){
    return localStorage.getItem("token")
  }
  
}
