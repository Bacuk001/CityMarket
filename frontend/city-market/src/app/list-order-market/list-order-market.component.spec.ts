import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOrderMarketComponent } from './list-order-market.component';

describe('ListOrderMarketComponent', () => {
  let component: ListOrderMarketComponent;
  let fixture: ComponentFixture<ListOrderMarketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListOrderMarketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListOrderMarketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
