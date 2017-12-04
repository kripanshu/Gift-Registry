import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SharedregistryComponent } from './sharedregistry.component';

describe('SharedregistryComponent', () => {
  let component: SharedregistryComponent;
  let fixture: ComponentFixture<SharedregistryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SharedregistryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SharedregistryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
