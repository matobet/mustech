import Ember from 'ember';

export default Ember.Controller.extend({
  needs: ['authentication'],
  actions: {
    close: function() {
      return this.send('closeModal');
    },
    login: function() {
      var _this = this;

      var defer = Ember.RSVP.defer();
      defer.promise.then(function(){
          return _this.send('closeModal');
        },
        function(){
          _this.notify.alert('Invalid email/password combination.');
        });

      var credentials = this.getProperties('identification', 'password');
      this.get('controllers.authentication').send('authenticate', credentials, defer);
    }
  }
});
