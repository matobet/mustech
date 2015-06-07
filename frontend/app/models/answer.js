import DS from 'ember-data';

export default DS.Model.extend({
  option: DS.belongsTo('option'),
  poll: DS.belongsTo('poll'),
  user: DS.belongsTo('user')
});
