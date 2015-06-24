import DS from 'ember-data';

export default DS.RESTSerializer.extend(DS.EmbeddedRecordsMixin, {
  attrs: {
    answers: { serialize: 'records', deserialize: 'ids' },
    poll: { deserialize: 'id' }
  }
});
