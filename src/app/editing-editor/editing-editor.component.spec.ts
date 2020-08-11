import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditingEditorComponent } from './editing-editor.component';

describe('EditingEditorComponent', () => {
  let component: EditingEditorComponent;
  let fixture: ComponentFixture<EditingEditorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditingEditorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditingEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
