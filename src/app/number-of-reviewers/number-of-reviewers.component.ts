import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServicesService } from '../services/services.service';

@Component({
  selector: 'app-number-of-reviewers',
  templateUrl: './number-of-reviewers.component.html',
  styleUrls: ['./number-of-reviewers.component.css']
})
export class NumberOfReviewersComponent implements OnInit {

  private taskId: string;
  private formFields: [];
  private numOfReviewers=-1;
  private processId:string;

  constructor(private dataService: ServicesService, private route: ActivatedRoute, private router: Router)
   {
    this.route.params.subscribe(params => { this.taskId = params["Id"]});
    debugger
        dataService.getTask(this.taskId).subscribe(
          res => {
            debugger
            console.log(res);
            this.formFields = res.formFields;
            this.processId = res.processInstanceId
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
      if(property=="NumberReviewers"){
        this.numOfReviewers = value[property];
      }   
    }    
    this.dataService.setNumberOfReviewers(this.numOfReviewers,this.taskId).subscribe(
      res => {

        this.dataService.submitTaskMagazine(o,this.taskId).subscribe(
          res => {
            console.log("Completeeeeeeee");
              this.router.navigate(['choosereviewers/' + this.processId + '/' + this.numOfReviewers]);
          }
        )
      }
    )
  }

}
