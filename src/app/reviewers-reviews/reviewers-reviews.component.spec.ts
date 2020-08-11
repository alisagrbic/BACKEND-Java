import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewersReviewsComponent } from './reviewers-reviews.component';

describe('ReviewersReviewsComponent', () => {
  let component: ReviewersReviewsComponent;
  let fixture: ComponentFixture<ReviewersReviewsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewersReviewsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewersReviewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
