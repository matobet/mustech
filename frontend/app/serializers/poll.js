import DS from 'ember-data';

export default DS.RESTSerializer.extend(DS.EmbeddedRecordsMixin, {
  attrs: {
    issue: { deserialize: 'id' },
    options: { serialize: 'records', deserialize: 'ids' },
    answers: { serialize: 'records', deserialize: 'ids' }
  }
});
