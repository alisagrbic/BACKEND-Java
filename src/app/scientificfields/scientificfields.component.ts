import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ServicesService } from '../services/services.service';

@Component({
  selector: 'app-scientificfields',
  templateUrl: './scientificfields.component.html',
  styleUrls: ['./scientificfields.component.css']
})
export class ScientificfieldsComponent implements OnInit {

  private formFields = [];
  private enumValues = [];
  private listOfScientificFileds = [];
  private processId: string = "";
  private scientificField = -1;
  private numOfFields = -1;
  private taskId = -1;

  constructor(private dataService: ServicesService, private route: ActivatedRoute, private router: Router) {
    this.route.params.subscribe(params => { this.processId = params["Id"] });
    this.route.params.subscribe(params => { this.numOfFields = params["IdFields"] });

    dataService.processById(this.processId).subscribe(
      res => {
        debugger
        console.log(res);
        this.formFields = res;
      },
      err => {
        console.log("Error occured");
      }
    )

    dataService.getScientificFields().subscribe(
      res => {
        debugger
        console.log(res);
        this.enumValues = res;
      }
    )
  }

  ngOnInit() {
  }

  onSubmit(value, form) {
    debugger
    let o = new Array();
    for (var property in value) {
      console.log(property);
      console.log(value[property]);
      o.push({ fieldId: property, fieldValue: value[property] });
    }

      
      this.numOfFields--;
      if (this.numOfFields == 0) {
        
        this.dataService.getTasks(this.processId).subscribe(
          res => {         
            for (let field of res) {
              this.taskId = field.taskId;
            }

            debugger
            this.listOfScientificFileds.push(o);
            this.dataService.addScientificFields(o, this.taskId).subscribe(
              res => {
                debugger
                alert("The email was sent")
                this.router.navigate(['login/' + this.processId]);

              }
            )
          }
        )
      }
      else {
        debugger       
        this.dataService.getTasks(this.processId).subscribe(
          res => {
            debugger
            for (let field of res) {
              this.taskId = field.taskId;
            }
            this.dataService.completeTask(this.taskId).subscribe(
              res => {
                this.router.navigate(['scientificfields/' + this.processId + "/" + this.numOfFields]);
                debugger
              }
            )
          }
        )
      }
  }
}
