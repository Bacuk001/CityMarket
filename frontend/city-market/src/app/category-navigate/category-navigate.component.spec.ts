import {browser, by, element, protractor} from 'protractor';

describe('TopNavigateComponent', () => {
  const selectCategory = element(by.id('category'));
  const listProduct = element(by.id('inOrder'));
  const orderTitle = element(by.id('orderTitle'));

  beforeEach(() => {
  });

  it('Click category', () => {
    selectCategory.click();
    expect(selectCategory.isEnabled()).toEqual(true);
  });

  it('Click in order', () => {
    const until = protractor.ExpectedConditions;
    browser.wait(until.presenceOf(listProduct), 3000);
    listProduct.click();
    expect(orderTitle.getText()).toEqual('Заявка на Приобретение товара');
  });
});





