import BasicAuthenticator from '../authenticators/basic';
import BasicAuthorizer from '../authorizers/basic';

export function initialize(container) {
  container.register('authenticator:basic', BasicAuthenticator);
  container.register('authorizer:basic', BasicAuthorizer);

  var session = container.lookup('simple-auth-session:main');
  var applicationRoute = container.lookup('route:application');

  session.addObserver('isAuthenticated', function() {
    if (session.get('isAuthenticated')) {
      let identification = session.get('secure.identification');
      let password = session.get('secure.password');
      applicationRoute.connect({identification, password});
    } else {
      applicationRoute.disconnect();
    }
  });
}

export default {
  name: 'authentication',
  after: 'simple-auth',
  initialize: initialize
};
