import Ember from 'ember';

export default Ember.Component.extend({
  actions: {
    close() {
      return this.sendAction();
    }
  }
});
