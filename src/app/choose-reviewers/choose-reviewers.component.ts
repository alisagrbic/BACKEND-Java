import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../services/services.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-choose-reviewers',
  templateUrl: './choose-reviewers.component.html',
  styleUrls: ['./choose-reviewers.component.css']
})
export class ChooseReviewersComponent implements OnInit {

  private processId:string;
  private numOfReviewers = -1;
  private formFields =[];
  private enumValues =[];
  private taskId:string;
  private listOfReviewers=[];

  constructor(private dataService: ServicesService, private route: ActivatedRoute, private router: Router) 
  {
    this.route.params.subscribe(params => { this.processId = params["Id"] });
    this.route.params.subscribe(params => { this.numOfReviewers = params["num"] });

    dataService.processById(this.processId).subscribe(
      res => {
        debugger
        console.log(res);
        this.formFields = res;

        dataService.getAllReviewers().subscribe(
          res => {
            debugger
            console.log(res);
            this.enumValues = res;
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

  ngOnInit() {
  }

  onSubmit(value, form){
    let o = new Array();
    for (var property in value) {
      console.log(property);
      console.log(value[property]);
      o.push({ fieldId: property, fieldValue: value[property] });
    }
    debugger
    this.listOfReviewers.push(o);  

      this.numOfReviewers--;
      if (this.numOfReviewers == 0) {
        
        this.dataService.getTasksMagazine(this.processId).subscribe(
          res => {         
            for (let field of res) {
              this.taskId = field.taskId;
            }

            debugger
            this.dataService.addReviewers(this.listOfReviewers, this.taskId).subscribe(
              res => {
                debugger
                alert("Reviewers have to do reviews");
              }
            )
          }
        )
      }
      else {
        debugger       
        this.dataService.getTasksMagazine(this.processId).subscribe(
          res => {
            debugger
            for (let field of res) {
              this.taskId = field.taskId;
            }
            this.dataService.completeTaskMagazine(this.taskId).subscribe(
              res => {
                this.router.navigate(['choosereviewers/' + this.processId + "/" + this.numOfReviewers]);
                debugger
              }
            )
          }
        )
      }
    }
  }