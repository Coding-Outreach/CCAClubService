var path = require('path');

module.exports = {
    entry: './src/main/js/index.js',
    devtool: 'sourcemaps',
    cache: true,
    mode: 'development',
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/bundle.js'
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react"]
                    }
                }]
            },
            {
                          test: /\.css$/,
                          use: ["style-loader", "css-loader"]
            },
            {
                    test: /\.(png|svg|jpg|jpeg|gif)$/i,
                    use: [{
                        loader: 'file-loader',
                        options: {
                            outputPath: './src/main/resources/static/',
                            publicPath: '.'
                        }
                    }]

            }
        ]
    }
};