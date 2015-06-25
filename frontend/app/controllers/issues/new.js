import Ember from 'ember';

export default Ember.Controller.extend({
  options: [],
  invitedUsers: [],
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
      let issue = this.store.createRecord('issue', {
        name: this.get('name'),
        description: this.get('description'),
        expiresAt: this.get('expiresAt')
      });

      let poll = this.store.createRecord('poll', {
        question: this.get('question')
      });


      this.get('options').forEach(option => {
        let record = this.store.createRecord('option', {
          value: option.value
        });
        poll.get('options').pushObject(record);
      });

      this.get('invitedUsers').forEach(user => {
        let record = this.store.createRecord('user', {
          email: user.value
        });
        issue.get('concernedUsers').pushObject(record);
      });

      issue.get('polls').pushObject(poll);

      issue.save()
        .then(() => {
          this.notify.info('Issue created!');
          this.transitionTo('issues');
        })
        .catch(e => {
          Ember.Logger.debug('Issue creation failed!', e);
          this.notify.alert('Issue creation failed!');
        });
    },
    addUserEmail() {
      let invitedUsers = this.get('invitedUsers');
      invitedUsers.pushObject({
        label: 'email'
      });
    },
    removeUserEmail(user) {
      this.get('invitedUsers').removeObject(user);
    }
  }
});
