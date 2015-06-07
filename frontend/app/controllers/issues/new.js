import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    save() {
      let record = this.store.createRecord('issue', this.getProperties('name', 'description'));
      record.save()
        .then(() => this.notify.info('Issue created!'))
        .catch(e => {
          Ember.Logger.debug('Issue creation failed!', e);
          this.notify.alert('Issue creation failed!');
        });
    }
  }
});
