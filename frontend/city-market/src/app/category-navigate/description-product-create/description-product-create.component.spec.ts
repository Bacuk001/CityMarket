import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DescriptionProductCreateComponent } from './description-product-create.component';

describe('DescriptionProductCreateComponent', () => {
  let component: DescriptionProductCreateComponent;
  let fixture: ComponentFixture<DescriptionProductCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DescriptionProductCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DescriptionProductCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
