import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NumberOfReviewersComponent } from './number-of-reviewers.component';

describe('NumberOfReviewersComponent', () => {
  let component: NumberOfReviewersComponent;
  let fixture: ComponentFixture<NumberOfReviewersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NumberOfReviewersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NumberOfReviewersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
