import Ember from 'ember';
import Base from 'simple-auth/authorizers/base';

export default Base.extend({
  authorize(xhr, options) {
    let username = this.get('session.secure.identification');
    let password = this.get('session.secure.password');
    if (this.get('session.isAuthenticated') && !Ember.isEmpty(username) && !Ember.isEmpty(password)) {
      options.username = username;
      options.password = password;
    }
  }
});
