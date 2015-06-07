import $ from 'jquery';

export function initialize() {
  $.material.init();
}

export default {
  name: 'bootstrap-material',
  initialize: initialize
};
