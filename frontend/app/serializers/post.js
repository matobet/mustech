import DS from 'ember-data';

export default DS.RESTSerializer.extend(DS.EmbeddedRecordsMixin, {
  attrs: {
    user: { deserialize: 'id' },
    issue: { deserialize: 'id' }
  }
});
