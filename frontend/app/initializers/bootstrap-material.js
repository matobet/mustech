import Ember from 'ember';
import $ from 'jquery';

export function initialize() {
  $.material.init();
  Ember.LinkView.reopen({
    attributeBindings: ['data-toggle']
  });
}

export default {
  name: 'bootstrap-material',
  initialize: initialize
};
