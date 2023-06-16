const pluginWebc = require("@11ty/eleventy-plugin-webc");

module.exports = function (config) {
  config.addPlugin(pluginWebc, {
    components: "_includes/webc/*.webc",
  });
};
