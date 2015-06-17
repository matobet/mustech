import DS from 'ember-data';

export default DS.Model.extend({
  name: DS.attr('string'),
  description: DS.attr('string'),

  createdBy: DS.belongsTo('user', { async: true }),
  createdAt: DS.attr('date'),
  expiresAt: DS.attr('date'),

  polls: DS.hasMany('poll', { async: true }),
  posts: DS.hasMany('post', { async: true })
});
