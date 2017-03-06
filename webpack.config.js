var path = require('path');
var webpack = require('webpack');

module.exports = {
    entry: './src/main/resources/static/main.js',
    output: { path: __dirname, filename: './src/main/resources/static/bundle.js' },
    module: {
        loaders: [{
            test: /.jsx?$/,
            loader: 'babel-loader',
            exclude: /node_modules/,
            query: {
                presets: ['es2015', 'react']
            }
        }]
    },
};