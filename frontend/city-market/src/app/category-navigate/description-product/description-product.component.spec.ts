import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DescriptionProductComponent } from './description-product.component';

describe('DescriptionProductComponent', () => {
  let component: DescriptionProductComponent;
  let fixture: ComponentFixture<DescriptionProductComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DescriptionProductComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DescriptionProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
