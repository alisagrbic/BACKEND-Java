import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServicesService } from '../services/services.service';

@Component({
  selector: 'app-review-pdf',
  templateUrl: './review-pdf.component.html',
  styleUrls: ['./review-pdf.component.css']
})
export class ReviewPDFComponent implements OnInit {

  private taskId: string;
  private formFields: [];
  private isFormatted:string;
  constructor(private dataService: ServicesService, private route: ActivatedRoute, private router: Router)
   {
     debugger
    this.route.params.subscribe(params => { this.taskId = params["Id"]});

    dataService.getTask(this.taskId).subscribe(
      res => {
        debugger
        console.log(res);
        this.formFields = res.formFields;
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
      o.push({ fieldId: property, fieldValue: value[property] });
      debugger
      if(property == "Formatted")
          if(value[property] == true)
            this.isFormatted = "true";
          else
            this.isFormatted = "false";
    }


    this.dataService.setFormatted(this.isFormatted, this.taskId).subscribe(
      res => {
        this.dataService.completeTask(this.taskId).subscribe(
          res => {
            this.router.navigate(["tasks"]);
          },
          err => {
            console.log("Error occured");
          }
        )
      },
      err => {
        console.log("Error occured");
      }
    )
  }

}
