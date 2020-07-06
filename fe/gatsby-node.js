const path = require('path');

module.exports = {
  onCreateWebpackConfig: function ({ actions }) {
    actions.setWebpackConfig({
      resolve: {
        alias: {
          '@': path.resolve(__dirname, 'src')
        }
      }
    })
  }
}