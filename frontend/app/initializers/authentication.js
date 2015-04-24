import BasicAuthenticator from '../authenticators/basic';
import BasicAuthorizer from '../authorizers/basic';

export function initialize(container) {
  // application.inject('route', 'foo', 'service:foo');
  container.register('authenticator:basic', BasicAuthenticator);
  container.register('authorizer:basic', BasicAuthorizer);
}

export default {
  name: 'authentication',
  before: 'simple-auth',
  initialize: initialize
};
