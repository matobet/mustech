import DS from 'ember-data';

export default DS.ModelFragment.extend({
  name: DS.attr('string'),
  email: DS.attr('string'),
  password: DS.attr('string')
});
