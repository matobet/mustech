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
      this.store.createRecord('user', {
        email: this.get('email'),
        password: this.get('password'),
        name: this.get('name')
      }).save()
        .then(() => {
          this.notify.info('Registration successful!');
          return this.send('closeModal');
        }, e => {
          Ember.Logger.debug('Registration failed!', e);
          this.notify.alert('Registration failed!');
        });
    }
  }
});
