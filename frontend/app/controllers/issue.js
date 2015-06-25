import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    vote: function(option) {
      option.get('answers').pushObject(this.store.createRecord('answer', {
        option: this.get('option'),
        poll: option.get('poll')
      }));
      option.save();
    }
  }
});
