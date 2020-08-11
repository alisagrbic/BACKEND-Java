import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../services/services.service';
import { ActivatedRoute, Router } from '@angular/router';
import {ArticleDto} from '../models/ArticleDto';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-review-text',
  templateUrl: './review-text.component.html',
  styleUrls: ['./review-text.component.css']
})
export class ReviewTextComponent implements OnInit {

  private taskId: string="";
  article: ArticleDto;
  private fields=[];
  private isRelavant: string;
  private processId: string;

  constructor(private dataService: ServicesService, private route: ActivatedRoute, private router: Router) 
  {
    this.route.params.subscribe(params => { this.taskId = params["Id"]});
    
    dataService.getNewArticle(this.taskId).subscribe(
      res => {
        debugger
        console.log(res);
        this.article = res;

        dataService.getTask(this.taskId).subscribe(
          res => {
            debugger
            console.log(res);
            this.fields = res.formFields;
            this.processId = res.processInstanceId;
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
      debugger
      if(property == "Relevant")
          if(value[property] == true)
            this.isRelavant = "true";
          else
            this.isRelavant = "false";
    }

    this.dataService.setRelevant(this.isRelavant, this.taskId).subscribe(
      res => {
        debugger
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
