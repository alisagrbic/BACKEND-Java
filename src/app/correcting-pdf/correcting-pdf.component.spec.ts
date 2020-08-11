import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CorrectingPdfComponent } from './correcting-pdf.component';

describe('CorrectingPdfComponent', () => {
  let component: CorrectingPdfComponent;
  let fixture: ComponentFixture<CorrectingPdfComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CorrectingPdfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CorrectingPdfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
