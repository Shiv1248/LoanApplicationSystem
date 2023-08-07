import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DocumentTypes } from '../LoanApplications';

@Injectable({
  providedIn: 'root'
})
export class GetDocService {


  constructor(private http:HttpClient) { }

  getDocumentTypes(): Observable<DocumentTypes[]> {
    return this.http.get<DocumentTypes[]>('http://localhost:9090/api/documenttypes');
  }
}
