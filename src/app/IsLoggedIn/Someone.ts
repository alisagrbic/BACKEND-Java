import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';

@Injectable()
export class Someone implements CanActivate {

  constructor() {}

  canActivate() {
    return localStorage.jwt ? true : false;
  }
}