import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCarFormComponent } from './car-form.component';

describe('LocationFormComponent', () => {
  let component: AddCarFormComponent;
  let fixture: ComponentFixture<AddCarFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddCarFormComponent]
    });
    fixture = TestBed.createComponent(AddCarFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
