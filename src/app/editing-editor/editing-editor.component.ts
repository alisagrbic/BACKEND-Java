import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServicesService } from '../services/services.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-editing-editor',
  templateUrl: './editing-editor.component.html',
  styleUrls: ['./editing-editor.component.css']
})
export class EditingEditorComponent implements OnInit {

  private taskId: string;
  private formFields:[];
  private processId: string;
  private accepted;
  private smallupdates;
  private bigupdates;

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

       /* dataService.getReviews(this.processId).subscribe(
          res=>{
            debugger
          },
          err => {
            console.log("Error occured");
          }
        )*/

        


   }

  ngOnInit() {
  }


  saveEditing(value, form: NgForm){
    
    if(value["AcceptedText"]==true)
      this.accepted="true"
    else
      this.accepted="false"

    if(value["AcceptedWithSmallUpdates"]==true)
      this.smallupdates="true"
    else
      this.smallupdates="false"
    
    if(value["AcceptedWithBigUpdates"]==true)
      this.bigupdates="true"
    else
      this.bigupdates="false"

    let body = new FormData();
    body.append('AcceptedText', this.accepted);
    body.append('AcceptedWithSmallUpdates', this.smallupdates);
    body.append('AcceptedWithBigUpdates', this.bigupdates);
    body.append('taskId', this.taskId);


    this.dataService.finishedReview(body).subscribe(
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
