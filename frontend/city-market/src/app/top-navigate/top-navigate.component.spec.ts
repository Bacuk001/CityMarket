import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TopNavigateComponent } from './top-navigate.component';

describe('TopNavigateComponent', () => {
  let component: TopNavigateComponent;
  let fixture: ComponentFixture<TopNavigateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TopNavigateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TopNavigateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
