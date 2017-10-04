import {by, element} from 'protractor';

describe('OrderUserComponent', () => {
  const userName = element(by.id('nameOrderUser'));
  const userContacts = element(by.id('userContacts'));
  const userAddress = element(by.id('userAddress'));
  const closeOrder = element(by.id('closeOrderUser'));

  beforeEach(() => {
  });

  it('Create order category', () => {
    userName.sendKeys('Jon smith');
    userContacts.sendKeys('+3752978265895');
    userAddress.sendKeys('BY, Grodno, TRE 7-15');
    expect(userName.getText()).toEqual('');
    closeOrder.click();
  });
});





