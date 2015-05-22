import BasicAuthenticator from '../authenticators/basic';
import BasicAuthorizer from '../authorizers/basic';

export function initialize(container) {
  container.register('authenticator:basic', BasicAuthenticator);
  container.register('authorizer:basic', BasicAuthorizer);
}

export default {
  name: 'authentication',
  after: 'simple-auth',
  initialize: initialize
};
