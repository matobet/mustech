import DS from 'ember-data';

export default DS.RESTSerializer.extend(DS.EmbeddedRecordsMixin, {
  attrs: {
    issue: { serialize: 'id' },
    options: { serialize: 'ids' },
    answers: { serialize: 'ids' }
  }
});
