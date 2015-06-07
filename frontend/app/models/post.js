import DS from 'ember-data';

export default DS.Model.extend({
  text: DS.attr('string'),
  timestamp: DS.attr('date'),

  user: DS.belongsTo('user'),
  issue: DS.belongsTo('issue')
});
