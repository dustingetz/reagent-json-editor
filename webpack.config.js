var webpack = require('webpack');
var path = require('path');

module.exports = {
  entry: {
    browser: './preamble'
  },

  output: {
    filename: 'generated-resources/preamble.js'
  },

  resolve: {
    extensions: ['', '.js'],
    root: [ path.resolve('.') ],
    modulesDirectories: ['node_modules']
  },

  plugins: [ new webpack.NoErrorsPlugin() ],

  module: {
    loaders: [
      { test: /\.css$/, loader: 'style!css' }
    ]
  }
};
