import DS from 'ember-data';

export default DS.Model.extend({
  question: DS.attr('string'),
  issue: DS.belongsTo('issue'),
  options: DS.hasMany('option'),
  answers: DS.hasMany('answer')
});
