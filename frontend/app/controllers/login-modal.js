import Ember from 'ember';

export default Ember.Controller.extend({
  needs: ['authenticator'],
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
      this.get('controllers.authenticator').send('authenticate', credentials, defer);
    }
  }
});
