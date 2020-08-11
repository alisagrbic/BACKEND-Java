import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../services/services.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ArticleDto } from '../models/ArticleDto';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-correcting-pdf',
  templateUrl: './correcting-pdf.component.html',
  styleUrls: ['./correcting-pdf.component.css']
})
export class CorrectingPdfComponent implements OnInit {

  private taskId: string;
  private formFields:[];
  private selectedPdf: File;
  private title: string;

  constructor(private dataService: ServicesService, private route: ActivatedRoute, private router: Router) 
  {

    this.route.params.subscribe(params => { this.taskId = params["Id"]});
debugger
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

  correctArticle(article: ArticleDto, form: NgForm){
    debugger
    let body = new FormData();
    body.append('pdf', this.selectedPdf);
    body.append('fileName', article.title);
    body.append('taskId', this.taskId);
    const articleJson = JSON.stringify(article);
    body.append("article", articleJson);

    this.dataService.correctArticle(body).subscribe(
      res => {
          this.router.navigate(['tasks']);
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
