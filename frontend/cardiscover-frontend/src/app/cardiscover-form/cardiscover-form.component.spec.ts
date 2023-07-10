import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardiscoverFormComponent } from './cardiscover-form.component';

describe('CardiscoverFormComponent', () => {
  let component: CardiscoverFormComponent;
  let fixture: ComponentFixture<CardiscoverFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CardiscoverFormComponent]
    });
    fixture = TestBed.createComponent(CardiscoverFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
