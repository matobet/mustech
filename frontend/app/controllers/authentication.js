import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    authenticate(credentials, defer) {
      this.get('session').authenticate('authenticator:basic', credentials)
        .then(function() {
          defer.resolve();
        }, function() {
          defer.reject();
          Ember.Logger.debug('Authentication failed!');
        });
    }
  }
});
