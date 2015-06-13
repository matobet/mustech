import DS from 'ember-data';

export default DS.Model.extend({
  value: DS.attr('string'),
  answers: DS.hasMany('answer'),
  poll: DS.belongsTo('poll')
});
