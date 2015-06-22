import DS from 'ember-data';

export default DS.Model.extend({
  text: DS.attr('string'),
  timestamp: DS.attr('date'),

  user: DS.belongsTo('user', { async: true }),
  issue: DS.belongsTo('issue', { async: true })
});
