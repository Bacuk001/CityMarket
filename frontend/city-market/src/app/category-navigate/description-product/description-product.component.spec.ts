import {browser, by, element, protractor} from 'protractor';

describe('DescriptionProductComponent', () => {
  const closeDescription = element(by.id('closeDescription'));
  const openDescription = element(by.id('inDescription'));

  beforeEach(() => {
  });

  it('Description open', () => {
    openDescription.click();
    const until = protractor.ExpectedConditions;
    browser.wait(until.presenceOf(closeDescription), 2000);
    closeDescription.click();
  });
});





