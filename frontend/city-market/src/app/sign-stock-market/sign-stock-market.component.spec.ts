import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SignStockMarketComponent } from './sign-stock-market.component';

describe('SignStockMarketComponent', () => {
  let component: SignStockMarketComponent;
  let fixture: ComponentFixture<SignStockMarketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SignStockMarketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SignStockMarketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
