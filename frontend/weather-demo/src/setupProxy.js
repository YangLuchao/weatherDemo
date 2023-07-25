const { createProxyMiddleware } = require('http-proxy-middleware')
 
module.exports = function (app) {
    app.use(
        createProxyMiddleware('/weather', { 
            target: 'http://localhost:9968',
            changeOrigin: true,
            pathRewrite: { '^/weather': '' }
        })
    )
}