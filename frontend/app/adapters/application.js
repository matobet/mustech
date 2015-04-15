import DS from 'ember-data';
import ENV from '../config/environment';

export default DS.RESTAdapter.extend({
  namespace: ENV.APP.API_PATH,
  host: ENV.APP.API_SERVER
});
