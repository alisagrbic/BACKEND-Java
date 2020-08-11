import { Component, OnInit } from '@angular/core';
import {ServicesService} from '../services/services.service';
import { Router } from '@angular/router';
import { UserDto } from '../models/UserDto';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  private formFields = [];
  private formFieldsDTO = null;
  private idOfProcess;
  private numOfFields;
  private user = new UserDto();
  private taskId=-1;

  constructor(private dataService : ServicesService, private router: Router) {

    let x = dataService.startProcess().subscribe(
      res=>{
        debugger
        console.log(res);
        this.formFieldsDTO = res;
        this.formFields = res.formFields;
        this.idOfProcess = res.processInstanceId;
        this.taskId = res.taskId;
      },
      err => {
        console.log("Error occured");
      }
    )
   }

  ngOnInit() {
  }

  onSubmit(value, form){
    let o = new Array();
    for (var property in value) {
      console.log(property);
      console.log(value[property]);
      o.push({fieldId : property, fieldValue : value[property]});
      debugger
      if(property=="num"){
        this.numOfFields = value[property];
      }   
    }     
      this.dataService.submitTask(o,this.taskId).subscribe(
        res=>{
          console.log("Completeeeeeeee");
          this.router.navigate(['scientificfields/' + this.idOfProcess + "/" + this.numOfFields]);
          debugger
        }
      )
    
  }

}
