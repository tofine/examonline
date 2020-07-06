const proxy = require("http-proxy-middleware")

module.exports = {
  plugins: [
    {
      resolve: 'gatsby-plugin-sass',
    },
    'gatsby-plugin-offline',
  ],
  developMiddleware: app => {
    app.use(
      "/api",
      proxy({
        target: "https://tool.chubanyun.me/node",
        secure: false, // Do not reject self-signed certificates.
        "changeOrigin": true,
        pathRewrite: {
          "^/api": "",
        },
      })
    )
  },
}