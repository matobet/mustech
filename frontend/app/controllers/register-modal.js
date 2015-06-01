import Ember from 'ember';

export default Ember.Controller.extend({
  email: null,
  password: null,
  name: null,

  actions: {
    close: function() {
      return this.send('closeModal');
    },
    register: function() {
      var _this = this;
      this.store.createRecord('user', {
        email: this.get('email'),
        password: this.get('password'),
        name: this.get('name')
      }).save()
        .then(function() {
          _this.notify.info('Registration successful!');
          return _this.send('closeModal');
        }, function(e) {
          Ember.Logger.debug('Registration failed!', e);
          _this.notify.alert('Registration failed!');
        });
    }
  }
});
