import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateRegistryComponent } from './create-registry.component';

describe('CreateRegistryComponent', () => {
  let component: CreateRegistryComponent;
  let fixture: ComponentFixture<CreateRegistryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateRegistryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateRegistryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
