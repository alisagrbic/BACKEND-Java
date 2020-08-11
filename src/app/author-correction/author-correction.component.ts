import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServicesService } from '../services/services.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-author-correction',
  templateUrl: './author-correction.component.html',
  styleUrls: ['./author-correction.component.css']
})
export class AuthorCorrectionComponent implements OnInit {

  private taskId: string;
  private formFields: [];
  private processId: string;
  private reply;
  private changes;

  constructor(private dataService: ServicesService, private route: ActivatedRoute, private router: Router) {
    this.route.params.subscribe(params => { this.taskId = params["Id"] });

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

  saveCorrection(value, form: NgForm) {

    if (value["ReplyComment"] == true)
      this.reply = "true"
    else
      this.reply = "false"

    if (value["Changes"] == true)
      this.changes = "true"
    else
      this.changes = "false"

    let body = new FormData();
    body.append('ReplyComment', this.reply);
    body.append('Changes', this.changes);
    body.append('taskId', this.taskId);


    this.dataService.saveChanges(body).subscribe(
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
