import DS from 'ember-data';

export default DS.RESTSerializer.extend(DS.EmbeddedRecordsMixin, {
  attrs: {
    user: { serialize: 'id' },
    issue: { serialize: 'id' }
  }
});
