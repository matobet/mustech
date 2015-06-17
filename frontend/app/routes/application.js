import Ember from 'ember';
import ENV from '../config/environment';
import ApplicationRouteMixin from 'simple-auth/mixins/application-route-mixin';

export default Ember.Route.extend(ApplicationRouteMixin, {
  actions: {
    logout() {
      this.get('session').invalidate()
        .then(() => {
          this.transitionTo('index');
        }, () => {
          Ember.Logger.debug('Session invalidation failed!');
        });
    },
    openModal(name, model) {
      this.render(name, {
        into: 'application',
        outlet: 'modal',
        model: model
      });
    },
    closeModal() {
      return this.disconnectOutlet({
        outlet: 'modal',
        parentView: 'application'
      });
    }
  },

  socketService: Ember.inject.service('websockets'),

  socket: null,

  connect({identification, password}) {
    let socket = this.get('socketService').socketFor(`ws://${identification}:${password}@${ENV.APP.WS_PATH}`);
    this.set('socket', socket);

    socket.on('open', this.openHandler, this);
    socket.on('message', this.messageHandler, this);
    socket.on('close', this.closeHandler, this);
  },

  disconnect() {
    let socket = this.get('socket');
    if (socket) {
      socket.close();
      this.set('socket', null);
    }
  },

  openHandler() {
    console.log('open');
  },

  messageHandler(data) {
    console.log('message: ' + JSON.stringify(data));
    // TODO: Fix getting store
    this.get('store').pushPayload(data);
  },

  closeHandler() {
    console.log('close');
  }
});
