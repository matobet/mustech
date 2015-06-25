import DS from 'ember-data';

export default DS.RESTSerializer.extend(DS.EmbeddedRecordsMixin, {
  attrs: {
    createdBy: { deserialize: 'id' },
    polls: { serialize: 'records', deserialize: 'ids' },
    posts: { serialize: 'records', deserialize: 'ids' },
    concernedUsers: { serialize: 'records', deserialize: 'ids' }
  }
});
