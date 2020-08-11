import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewTextComponent } from './review-text.component';

describe('ReviewTextComponent', () => {
  let component: ReviewTextComponent;
  let fixture: ComponentFixture<ReviewTextComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewTextComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewTextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
