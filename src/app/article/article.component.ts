import { Component, OnInit } from '@angular/core';
import { ActivatedRoute} from '@angular/router';
import { Router } from '@angular/router';
import { ServicesService } from '../services/services.service';
import { ArticleDto } from '../models/ArticleDto';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})
export class ArticleComponent implements OnInit {

  private processId: string = "";
  private formFields = [];
  selectedPdf: File;
  private taskId: string;

  constructor(private dataService: ServicesService, private route: ActivatedRoute, private router: Router) {
    this.route.params.subscribe(params => { this.processId = params["IdProcess"]});

    dataService.taskByIdMagazine(this.processId).subscribe(
      res => {
        debugger
        console.log(res);
        this.formFields = res;
      },
      err => {
        console.log("Error occured");
      }
    )

    dataService.getTasksMagazine(this.processId).subscribe(
      res => {
        debugger
        for (let field of res) {
          this.taskId = field.taskId;
        } 
      },
      err => {
        console.log("Error occured");
      }
    )
   }

  ngOnInit() {
  }

  addArticle(article: ArticleDto, form: NgForm){
     debugger
    let body = new FormData();
    body.append('pdf', this.selectedPdf);
    body.append('fileName', article.title);
    body.append('processInstanceId', this.processId);
    body.append('taskId', this.taskId);

    const articleJson = JSON.stringify(article);
    body.append("article", articleJson);   

    this.dataService.addArticle(body).subscribe(
      res => { 
        debugger
        this.router.navigate(["tasks"]);
      },  
      err => {
        console.log("Error occured");
      }
    )

  }

  onSelectPdf(event: any) {
    this.selectedPdf = event.target.files[0];
  }

}
