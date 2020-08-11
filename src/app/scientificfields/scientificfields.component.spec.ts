import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ScientificfieldsComponent } from './scientificfields.component';

describe('ScientificfieldsComponent', () => {
  let component: ScientificfieldsComponent;
  let fixture: ComponentFixture<ScientificfieldsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ScientificfieldsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ScientificfieldsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
