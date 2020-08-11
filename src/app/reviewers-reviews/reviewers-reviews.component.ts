import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../services/services.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-reviewers-reviews',
  templateUrl: './reviewers-reviews.component.html',
  styleUrls: ['./reviewers-reviews.component.css']
})
export class ReviewersReviewsComponent implements OnInit {

  private taskId: string;
  private formFields: [];
  private accept;
  private smallupdates;
  private bigupdates;
  private refuse;
  constructor(private dataService: ServicesService, private route: ActivatedRoute, private router: Router) 
  { 
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

  saveReview(value, form: NgForm){

    if(value["Accept"]==true)
      this.accept="true"
    else
      this.accept="false"

    if(value["AcceptWithSmallUpdates"]==true)
      this.smallupdates="true"
    else
      this.smallupdates="false"
    
    if(value["AcceptWithBigUpdates"]==true)
      this.bigupdates="true"
    else
      this.bigupdates="false"

    if(value["Refuse"]==true)
      this.refuse="true"
    else
      this.refuse="false"
    

    let body = new FormData();
    body.append('Comment', value["comment"]);
    body.append('Accept', this.accept);
    body.append('AcceptWithSmallUpdates', this.smallupdates);
    body.append('AcceptWithBigUpdates', this.bigupdates);
    body.append('Refuse', this.refuse);
    body.append('taskId', this.taskId);


    this.dataService.addReview(body).subscribe(
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
