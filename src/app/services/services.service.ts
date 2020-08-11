import { Injectable } from '@angular/core';

import { Headers, RequestOptions, ResponseContentType } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServicesService {

  constructor(private httpClient: HttpClient, private http : Http) { }


  startProcess(){
    return this.httpClient.get('http://localhost:8082/registration/get') as Observable<any>
  }

  processById(processId){
    return this.httpClient.get('http://localhost:8082/registration/get/tasks/' + processId) as Observable<any>
  }

  submitTask(form, taskId){
    return this.httpClient.post('http://localhost:8082/registration/post/'.concat(taskId), form) as Observable<any>
  }

  getScientificFields(){
    return this.httpClient.get('http://localhost:8082/registration/get/sci') as Observable<any>
  }

  completeTask(taskId){
    return this.httpClient.post('http://localhost:8082/registration/tasks/complete/' + taskId, null) as Observable<any>
  }

  getTasks(processId){
    return this.httpClient.get('http://localhost:8082/registration/get/firstTask/' + processId) as Observable<any>
  }

  addUser(user){
    return this.httpClient.get('http://localhost:8082/registration/post/addUser/' + user) as Observable<any>
  }

  addScientificFields(sciField, taskId){
    return this.httpClient.post('http://localhost:8082/registration/addSciFieldForUser/'.concat(taskId), sciField) as Observable<any>
  }

  getAllMagazines(){
    return this.httpClient.get('http://localhost:8082/magazine/AllMagazines') as Observable<any>
  }

  completeTaskMagazine(taskId){
    return this.httpClient.post('http://localhost:8082/magazine/tasks/complete/' + taskId, null) as Observable<any>
  }

  getTasksMagazine(processId){
    return this.httpClient.get('http://localhost:8082/magazine/get/firstTask/' + processId) as Observable<any>
  }

  taskByIdMagazine(processId){
    return this.httpClient.get('http://localhost:8082/magazine/get/tasks/' + processId) as Observable<any>
  }

  submitTaskMagazine(form, taskId){
    return this.httpClient.post('http://localhost:8082/magazine/post/'.concat(taskId), form) as Observable<any>
  }

  getMagazineByTitle(title, proccessId){
    return this.httpClient.get('http://localhost:8082/magazine/getMagazineByTitle/' + title + "/" + proccessId) as Observable<any>
  }

  addArticle(form){
    return this.httpClient.post('http://localhost:8082/magazine/addArticle', form) as Observable<any>
  }

  getTasksForUser(){
    return this.httpClient.get('http://localhost:8082/magazine/getTasksForUser') as Observable<any>
  }

  getNewArticle(taskId){
    return this.httpClient.get('http://localhost:8082/magazine/getNewArticle/'.concat(taskId)) as Observable<any>
  }

  getTask(taskId){
    return this.httpClient.get('http://localhost:8082/magazine/get/'.concat(taskId)) as Observable<any>
  }

  setRelevant(relevant, taskId){
    return this.httpClient.post('http://localhost:8082/magazine/setRelevant/' + relevant, taskId) as Observable<any>
  }

  setFormatted(formatted, taskId){
    return this.httpClient.post('http://localhost:8082/magazine/setFormatted/' + formatted, taskId) as Observable<any>
  }

  correctArticle(form){
    return this.httpClient.post('http://localhost:8082/magazine/correctArticle/', form) as Observable<any>
  }

  getAllReviewers(){
    return this.httpClient.get('http://localhost:8082/magazine/AllReviewers') as Observable<any>
  }

  addReviewers(reviewers, taskId){
    return this.httpClient.post('http://localhost:8082/magazine/addReviewers/'.concat(taskId), reviewers) as Observable<any>
  }

  
  setNumberOfReviewers(num, taskId){
    return this.httpClient.post('http://localhost:8082/magazine/setNumberOfReviewers/' + num, taskId) as Observable<any>
  }

  addReview(form){
    return this.httpClient.post('http://localhost:8082/magazine/addReview', form) as Observable<any>
  }

  getReviews(processId){
    return this.httpClient.get('http://localhost:8082/magazine/GetReviews/'.concat(processId)) as Observable<any>
  }

  finishedReview(form){
    return this.httpClient.post('http://localhost:8082/magazine/FinishedReview', form) as Observable<any>
  }

  saveChanges(form){
    return this.httpClient.post('http://localhost:8082/magazine/saveChanges', form) as Observable<any>
  }

  finalAcceptance(form){
    return this.httpClient.post('http://localhost:8082/magazine/FinalAcceptance', form) as Observable<any>
  }
}