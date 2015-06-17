/* jshint node: true */

module.exports = function(environment) {
  var ENV = {
    modulePrefix: 'frontend',
    environment: environment,
    baseURL: '/',
    locationType: 'hash',
    contentSecurityPolicy: {
      'default-src': "'self'",
      'script-src': "'self' 'unsafe-inline' 'unsafe-eval'",
      'font-src': "'self' data",
      'connect-src': "'self'",
      'img-src': "'self'",
      'style-src': "'self' 'unsafe-inline'"
    },
    EmberENV: {
      FEATURES: {
        // Here you can enable experimental features on an ember canary build
        // e.g. 'with-controller': true
      }
    },

    'simple-auth': {
      authorizer: 'authorizer:basic',
      store: 'simple-auth-session-store:cookie'
    },

    'simple-auth-cookie-store': {
      // expires on browser close
      cookieExpirationTime: null
    },

    APP: {
      // Here you can pass flags/options to your application instance
      // when it is created
      API_PATH: 'api',
      API_SERVER: 'http://localhost:8080/mustech',
      WS_PATH: 'localhost:8080/mustech/ws'
    }
  };

  if (environment === 'development') {
    // ENV.APP.LOG_RESOLVER = true;
    // ENV.APP.LOG_ACTIVE_GENERATION = true;
    // ENV.APP.LOG_TRANSITIONS = true;
    // ENV.APP.LOG_TRANSITIONS_INTERNAL = true;
    // ENV.APP.LOG_VIEW_LOOKUPS = true;
    //ENV.APP.API_PATH = 'mustech/api';
    //ENV.APP.API_SERVER = 'http://localhost:8080';
  }

  if (environment === 'test') {
    // Testem prefers this...
    ENV.baseURL = '/';
    ENV.locationType = 'none';

    // keep test console output quieter
    ENV.APP.LOG_ACTIVE_GENERATION = false;
    ENV.APP.LOG_VIEW_LOOKUPS = false;

    ENV.APP.rootElement = '#ember-testing';
  }

  if (environment === 'production') {

  }

  return ENV;
};
