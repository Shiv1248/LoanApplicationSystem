import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerRegistrationService {

  constructor(private http: HttpClient) {}

  public doRegistration(customer:Customer) {
    return this.http.post('http://localhost:9090/customer', customer, {
      responseType: 'text' as 'json',
    });
  }
}
