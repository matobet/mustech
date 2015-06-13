import Ember from 'ember';
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

  activate() {
    // TODO: propagate user credentials via websocket
    let socket = this.get('socketService').socketFor(`ws://${window.location.host}${document.location.pathname.replace(/[^/]*$/, '')}ws`);

    socket.on('open', this.openHandler, this);
    socket.on('message', this.messageHandler, this);
    socket.on('close', this.closeHandler, this);
  },

  openHandler() {
    console.log('open');
  },

  messageHandler(data) {
    console.log('message: ' + JSON.stringify(data));
    this.store.push(data.type, data.item);
  },

  closeHandler() {
    console.log('close');
  }
});
