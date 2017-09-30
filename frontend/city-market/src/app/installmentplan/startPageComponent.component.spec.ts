import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { startPageComponent } from './startPageComponent.component';

describe('InstallmentplanComponent', () => {
  let component: startPageComponent;
  let fixture: ComponentFixture<startPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ startPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(startPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
