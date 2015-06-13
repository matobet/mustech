import DS from 'ember-data';

export default DS.RESTSerializer.extend(DS.EmbeddedRecordsMixin, {
  attrs: {
    answers: { serialize: 'ids' },
    poll: { serialize: 'id' }
  }
});
