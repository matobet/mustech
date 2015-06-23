import Ember from 'ember';

export default Ember.Controller.extend({
  numberOfActiveEvents: 0,
  init() {
    this._super();
    this.store.find('issue').then(issues => {
      this.set('numberOfActiveEvents', issues.get('length'));
    });
  }
});
