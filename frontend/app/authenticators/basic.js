import Ember from 'ember';
import Base from 'simple-auth/authenticators/base';

import ENV from '../config/environment';

const ROOT_URL = (ENV.APP.API_SERVER || '') + '/' + ENV.APP.API_PATH;

export default Base.extend({
  authEndpoint: ROOT_URL + '/login',
  logoutEndpoint: ROOT_URL + '/logout',

  restore(data) {
    return new Ember.RSVP.Promise((resolve, reject) => {
      if (Ember.isEmpty(data.identification) || Ember.isEmpty(data.password)) {
        reject();
      } else {
        resolve(data);
      }
    });
  },

  authenticate(credentials) {
    return new Ember.RSVP.Promise((resolve, reject) => {
      Ember.$.ajax({
        url: this.authEndpoint,
        type: 'POST',
        xhrFields: { withCredentials: true },
        username: credentials.identification,
        password: credentials.password
      }).then(() => {
        Ember.run(() => resolve(credentials));
      }, xhr => {
        Ember.run(() => reject(xhr.responseText));
      });
    });
  },

  invalidate() {
    return new Ember.RSVP.Promise(resolve => {
      Ember.$.ajax({
        url: this.logoutEndpoint,
        type: 'POST'
      }).always(resolve);
    });
  }
});
