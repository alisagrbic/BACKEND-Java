import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../services/services.service';
import { TaskDto } from '../models/TaskDto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  private taskList=[];

  constructor(private dataService: ServicesService, private router: Router)
   { 
    dataService.getTasksForUser().subscribe(
      res => {
        debugger
        console.log(res);
        this.taskList = res;
      },
      err => {
        console.log("Error occured");
      }
    )
   }

  ngOnInit() {
  }

  getTask(task: TaskDto) {
    debugger
    if (task.name == "The editor reviews the text") {
      this.router.navigate(['reviewtext/'.concat(task.taskId)]);
    }
    else if (task.name == "Review PDF") {
      this.router.navigate(['reviewpdf/'.concat(task.taskId)]);
    }
    else if (task.name == "Author correcting PDF") {
      this.router.navigate(['correctingpdf/'.concat(task.taskId)]);
    }
    else if (task.name == "Number od reviewers") {
      this.router.navigate(['numberofreviewers/'.concat(task.taskId)]);
    }
    else if (task.name == "Reviewers do reviews") {
      this.router.navigate(['reviewersdoreviews/'.concat(task.taskId)]);
    }
    else if (task.name == "Editing editors") {
      this.router.navigate(['editingeditors/'.concat(task.taskId)]);
    }
    else if (task.name == "The author does the correction") {
      this.router.navigate(['authorcorrection/'.concat(task.taskId)]);
    }
    else if (task.name == "Editor reviews the corrections") {
      this.router.navigate(['editorreviewscorrection/'.concat(task.taskId)]);
    }
  
  }
}
