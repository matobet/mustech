import Ember from 'ember';
import Session from 'simple-auth/session';

export default Ember.Controller.extend({
  actions: {
    authenticate: function() {
      var _this = this;
      var credentials = this.getProperties('identification', 'password');
      this.get('session').authenticate('authenticator:basic', credentials)
        .then(function() {
          // authentication was successful
        }, function() {
          Ember.Logger.debug('Authentication failed!');
          _this.notify.alert('Invalid email/password combination.');
        });
    },
    logout: function() {
      var _this = this;
      this.get('session').invalidate()
        .then(function() {
          _this.transitionTo('index');
        }, function() {
          Ember.Logger.debug('Session invalidation failed!');
        });
    }
  }
});
