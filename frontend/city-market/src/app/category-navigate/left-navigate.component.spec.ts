import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LeftNavigateComponent } from './left-navigate.component';

describe('LeftNavigateComponent', () => {
  let component: LeftNavigateComponent;
  let fixture: ComponentFixture<LeftNavigateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LeftNavigateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LeftNavigateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
