import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewPDFComponent } from './review-pdf.component';

describe('ReviewPDFComponent', () => {
  let component: ReviewPDFComponent;
  let fixture: ComponentFixture<ReviewPDFComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewPDFComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewPDFComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
