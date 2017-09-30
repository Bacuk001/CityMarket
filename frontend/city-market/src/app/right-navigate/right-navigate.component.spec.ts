import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RightNavigateComponent } from './right-navigate.component';

describe('RightNavigateComponent', () => {
  let component: RightNavigateComponent;
  let fixture: ComponentFixture<RightNavigateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RightNavigateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RightNavigateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
