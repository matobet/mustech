import DS from 'ember-data';

export default DS.RESTSerializer.extend(DS.EmbeddedRecordsMixin, {
  attrs: {
    option: { deserialize: 'id' },
    poll: { deserialize: 'id' },
    user: { deserialize: 'id' }
  }
});
