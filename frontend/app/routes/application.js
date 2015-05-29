import Ember from 'ember';
import ApplicationRouteMixin from 'simple-auth/mixins/application-route-mixin';

export default Ember.Route.extend(ApplicationRouteMixin, {
  actions: {
    logout: function() {
      console.log("here");
      var _this = this;
      this.get('session').invalidate()
        .then(function() {
          _this.transitionTo('index');
        }, function() {
          Ember.Logger.debug('Session invalidation failed!');
        });
    },
    openModal: function(name, model) {
      this.render(name, {
        into: 'application',
        outlet: 'modal',
        model: model
      });
    },
    closeModal: function() {
      return this.disconnectOutlet({
        outlet: 'modal',
        parentView: 'application'
      });
    }
  }
});
