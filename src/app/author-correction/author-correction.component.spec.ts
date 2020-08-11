import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthorCorrectionComponent } from './author-correction.component';

describe('AuthorCorrectionComponent', () => {
  let component: AuthorCorrectionComponent;
  let fixture: ComponentFixture<AuthorCorrectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AuthorCorrectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthorCorrectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
