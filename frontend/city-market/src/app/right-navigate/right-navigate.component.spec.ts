import {browser, by, element, protractor} from 'protractor';

describe('OrderUserComponent', () => {
  const inputPassword = element(by.id('inputPassword'));
  const inputLogin = element(by.id('inputLogin'));
  const loginButton = element(by.id('loginButton'));
  const userName = element(by.id('userName'));

  beforeEach(() => {
  });

  it('enter password', () => {
    inputPassword.sendKeys('123');
    inputLogin.sendKeys('vasik');
    loginButton.click();
    const until = protractor.ExpectedConditions;
    browser.wait(until.presenceOf(userName), 2000);
    expect(userName.getText()).toEqual('vasik');
  });
});





