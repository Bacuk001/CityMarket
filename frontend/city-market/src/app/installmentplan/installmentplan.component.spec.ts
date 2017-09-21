import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InstallmentplanComponent } from './installmentplan.component';

describe('InstallmentplanComponent', () => {
  let component: InstallmentplanComponent;
  let fixture: ComponentFixture<InstallmentplanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InstallmentplanComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InstallmentplanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
