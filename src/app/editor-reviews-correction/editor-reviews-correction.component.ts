import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../services/services.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-editor-reviews-correction',
  templateUrl: './editor-reviews-correction.component.html',
  styleUrls: ['./editor-reviews-correction.component.css']
})
export class EditorReviewsCorrectionComponent implements OnInit {

  private taskId: string;
  private formFields:[];
  private processId:string;
  private finalAcceptance;

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

  saveFinal(value, form: NgForm){

    if(value["FinalAcceptance"]==true)
      this.finalAcceptance="true"
    else
      this.finalAcceptance="false"

    let body = new FormData();
    body.append('FinalAcceptance', this.finalAcceptance);
    body.append('taskId', this.taskId);


    this.dataService.finalAcceptance(body).subscribe(
      res => { 
        debugger
        this.router.navigate(["tasks"]);
      },  
      err => {
        console.log("Error occured");
      }
    )

  }
}
