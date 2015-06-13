import DS from 'ember-data';

export default DS.RESTSerializer.extend(DS.EmbeddedRecordsMixin, {
  attrs: {
    option: { serialize: 'id' },
    poll: { serialize: 'id' },
    user: { serialize: 'id' }
  }
});
