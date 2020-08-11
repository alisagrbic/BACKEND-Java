import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ServicesService } from '../services/services.service';
import { NgForm } from '@angular/forms';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private formFields = [];
  private processId: string = "";
  private loginService: LoginService;
  private formFieldsDto = null;
  currentTaskId: string;

  constructor(private dataService: ServicesService, private route: ActivatedRoute, private service: LoginService,
    private router: Router) {
    this.route.params.subscribe(params => { this.processId = params["Id"] });

    this.loginService = service;
    debugger
    let x = this.loginService.startLoginProcess().subscribe(
      res => {
        debugger
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processId = res.processInstanceId;
        this.currentTaskId = res.taskId;
      },
      () => {
        console.log("Error occured");
      }
    );



  }

  ngOnInit() {


  }

  logIn(user: any, form: NgForm) {
    let o = new Array();
debugger
    for (var property in user) {
      o.push({
        fieldId: property, fieldValue: user[property]
      });
    }

    this.loginService.getTheToken(o, this.currentTaskId, this.processId);

  }


}
