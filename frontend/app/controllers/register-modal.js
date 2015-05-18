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
        .then(function() {
          console.log("success");
          //TODO: close modal
          //TODO: notifications on success
        }, function() {
          console.log("error");
          //TODO: notifications on failure
        });
    }
  }
});
