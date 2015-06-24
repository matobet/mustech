import Ember from 'ember';

export default Ember.Controller.extend({

    actions: {
        vote: function(optionId) {
            //var options = this.get('options');
            this.notify.alert(optionId);
            //var vote = this.store.createRecord('answer', {
            //    option: options.findBy('id', '' + optionId),
            //    poll: this.get('model'),
            //    user: this.store.find('user')
            //});
            //vote.save();
        }
    }
});