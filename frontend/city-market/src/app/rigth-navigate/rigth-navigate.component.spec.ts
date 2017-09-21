import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RigthNavigateComponent } from './rigth-navigate.component';

describe('RigthNavigateComponent', () => {
  let component: RigthNavigateComponent;
  let fixture: ComponentFixture<RigthNavigateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RigthNavigateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RigthNavigateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
