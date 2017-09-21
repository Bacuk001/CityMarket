import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BodyApplicationComponent } from './body-application.component';

describe('BodyApplicationComponent', () => {
  let component: BodyApplicationComponent;
  let fixture: ComponentFixture<BodyApplicationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BodyApplicationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BodyApplicationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
