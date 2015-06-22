import DS from 'ember-data';

export default DS.Model.extend({
  option: DS.belongsTo('option', { async: true }),
  poll: DS.belongsTo('poll', { async: true }),
  user: DS.belongsTo('user', { async: true })
});
