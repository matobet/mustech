/* global require, module */

var EmberApp = require('ember-cli/lib/broccoli/ember-app');

var app = new EmberApp();

app.import('bower_components/bootstrap/dist/css/bootstrap.min.css');
app.import('bower_components/bootstrap-material-design/dist/css/material.min.css');
app.import('bower_components/bootstrap-material-design/dist/css/ripples.min.css');

app.import('bower_components/jquery/dist/jquery.min.js');
app.import('bower_components/arrive/minified/arrive.min.js');
app.import('bower_components/bootstrap/dist/js/bootstrap.min.js');
app.import('bower_components/bootstrap-material-design/dist/js/material.min.js');
app.import('bower_components/bootstrap-material-design/dist/js/ripples.min.js');

module.exports = app.toTree();
