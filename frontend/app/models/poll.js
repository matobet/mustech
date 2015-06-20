import DS from 'ember-data';

export default DS.Model.extend({
  question: DS.attr('string'),
  issue: DS.belongsTo('issue', { async: true }),
  options: DS.hasMany('option', { async: true }),
  answers: DS.hasMany('answer', { async: true })
});
