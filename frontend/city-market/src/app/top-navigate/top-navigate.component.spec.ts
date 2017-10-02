import {TopNavigateComponent} from './top-navigate.component';
import {browser, by, element, protractor} from 'protractor';

describe('TopNavigateComponent', () => {
  const selectMarket = element(by.id('selectMarket'));
  const optionMarket = element(by.tagName('option'));

  beforeEach(() => {
  });

  it('Select market', () => {
    selectMarket.click();
    const until = protractor.ExpectedConditions;
    browser.wait(until.presenceOf(selectMarket), 2000);
    optionMarket.click();
  })
});





