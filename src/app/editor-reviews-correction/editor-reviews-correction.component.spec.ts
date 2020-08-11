import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditorReviewsCorrectionComponent } from './editor-reviews-correction.component';

describe('EditorReviewsCorrectionComponent', () => {
  let component: EditorReviewsCorrectionComponent;
  let fixture: ComponentFixture<EditorReviewsCorrectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditorReviewsCorrectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditorReviewsCorrectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
