import { Component, OnInit } from '@angular/core';
import { Magazine } from '../models/Magazine';
import { ServicesService } from '../services/services.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-magazine',
  templateUrl: './magazine.component.html',
  styleUrls: ['./magazine.component.css']
})
export class MagazineComponent implements OnInit {

  private magazines: [];
  private service: ServicesService;
  private title;
  private magazine: Magazine;
  private processId: string = "";
  private formFields = [];
  private taskId: string = "";
  private magazineId: string = "";

  constructor(private dataService: ServicesService, private router: Router, private route: ActivatedRoute) { 
    this.route.params.subscribe(params => { this.processId = params["Id"]});
    this.service = dataService;
    
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

    dataService.getAllMagazines().subscribe(
      res => {
        debugger
        console.log(res);
        this.magazines = res;
      }
    )

  }

  ngOnInit() {   
  }

   onClick(magazine: Magazine) {
    debugger

    this.service.getMagazineByTitle(magazine.title, this.processId).subscribe(
      res=>{
        this.magazine = res;
        this.magazineId = this.magazine.id;

        this.service.getTasksMagazine(this.processId).subscribe(
          res=>{          
            for (let field of res) {
              this.taskId = field.taskId;
            }

            this.service.completeTaskMagazine(this.taskId).subscribe(
              res => {
                this.router.navigate(['article/' + this.processId + '/' + this.magazine.id]);
              }
            )
          }
        )    
    },
      error=>{
        alert("nije uslepo");
      })

  }

} 
