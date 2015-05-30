import Ember from 'ember';

export default Ember.Controller.extend({
  needs: ['authentication'],
  actions: {
    close() {
      return this.send('closeModal');
    },
    login() {
      var defer = Ember.RSVP.defer();
      defer.promise.then(() => {
          return this.send('closeModal');
        },
        () => {
          this.notify.alert('Invalid email/password combination.');
        });

      var credentials = this.getProperties('identification', 'password');
      this.get('controllers.authentication').send('authenticate', credentials, defer);
    }
  }
});
