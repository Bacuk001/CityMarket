// Protractor configuration file, see link for more information
// https://github.com/angular/protractor/blob/master/lib/config.ts

const {SpecReporter} = require('jasmine-spec-reporter');

exports.config = {
  allScriptsTimeout: 11000,
  specs: [
    './e2e/**/*.e2e-spec.ts',
    './src/app/top-navigate/top-navigate.component.spec.ts',
    './src/app/category-navigate/category-navigate.component.spec.ts',
    './src/app/category-navigate/order-user/order-user.component.spec.ts',
    './src/app/category-navigate/description-product/description-product.component.spec.ts',
    './src/app/right-navigate/right-navigate.component.spec.ts'
  ],
  capabilities: {
    'browserName': 'chrome'
  },
  directConnect: true,
  baseUrl: 'http://localhost:4200/',
  framework: 'jasmine',
  jasmineNodeOpts: {
    showColors: true,
    defaultTimeoutInterval: 30000,
    print: function () {
    }
  },
  onPrepare() {
    require('ts-node').register({
      project: 'e2e/tsconfig.e2e.json'
    });
    jasmine.getEnv().addReporter(new SpecReporter({spec: {displayStacktrace: true}}));
  }
};
