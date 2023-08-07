import {Component} from '@angular/core';
import { Router } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  standalone: true,
  imports: [NavbarComponent],
})
export class HomeComponent {

  constructor(private router:Router){}

  customer(path: string){
    this.router.navigate([path]);
  }

  bankManager(path: string){
    this.router.navigate([path]);
  }
 
}