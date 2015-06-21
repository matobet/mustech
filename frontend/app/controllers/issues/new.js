import Ember from 'ember';

export default Ember.Controller.extend({
  options: [],
  actions: {
    addPollOption() {
      let options = this.get('options');
      options.pushObject({
        label: 'Option'
      });
    },
    removePollOption(option) {
      this.get('options').removeObject(option);
    },
    save() {
      //TODO: save polls with issue
      let issue = this.store.createRecord('issue', this.getProperties('name', 'description', 'expiresAt'));
      issue.save()
        .then(() => this.notify.info('Issue created!'))
        .catch(e => {
          Ember.Logger.debug('Issue creation failed!', e);
          this.notify.alert('Issue creation failed!');
        });
    }
  }
});
