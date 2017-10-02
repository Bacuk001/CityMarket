import {browser} from 'protractor';

describe('City-market App', () => {

  beforeEach(() => {
    browser.get('');
  });

  it('should display welcome message', () => {
    browser.getTitle().then(title => {
      expect(title).toEqual('CityMarket');
    });
  });
});
