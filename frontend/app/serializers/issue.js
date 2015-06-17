import DS from 'ember-data';

export default DS.RESTSerializer.extend(DS.EmbeddedRecordsMixin, {
  attrs: {
    createdBy: { serialize: 'id' },
    polls: { serialize: 'ids' },
    posts: { serialize: 'ids' }
  }
});