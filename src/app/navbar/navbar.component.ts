import { Component, OnInit } from '@angular/core';
import { Someone } from '../IsLoggedIn/Someone';
import { Admin } from '../IsLoggedIn/Admin';
import { Author } from '../IsLoggedIn/Author';
import { Editor } from '../IsLoggedIn/Editor';
import { Reviewer } from '../IsLoggedIn/Reviewer';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  loggedIn: boolean;

  constructor(private isSomeone: Someone, private isAdmin: Admin, private isAuthor: Author,
               private isEditor: Editor, private isReviewer: Reviewer,
               private service: LoginService, private router: Router) { 

                this.service.currentLoginState.subscribe(loggedIn => this.loggedIn = loggedIn);
               }

  ngOnInit() {
    this.loggedIn = this.isSomeone.canActivate();
  }

  loggedAdmin(){
    return this.isAdmin.canActivate();
  }

  loggedAuthor(){
    return this.isAuthor.canActivate();
  }

  loggedEditor(){
    return this.isEditor.canActivate();
  }

  loggedReviewer(){
    return this.isReviewer.canActivate();
  }

  logOut(){
    localStorage.removeItem('jwt');
    localStorage.removeItem('role');
    this.loggedIn = this.isSomeone.canActivate();
    this.router.navigate(['login']);
  }

}
