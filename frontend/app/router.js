import Ember from 'ember';
import config from './config/environment';

var Router = Ember.Router.extend({
  location: config.locationType
});

export default Router.map(function() {
  this.route('events');
  this.route('users');
  this.resource('users');

  this.resource('issues', function () {
    this.route('new');
  });
});
