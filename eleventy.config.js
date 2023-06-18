const pluginWebc = require("@11ty/eleventy-plugin-webc");

module.exports = function (config) {
  config.addPlugin(pluginWebc, {
    components: "src/_includes/components/*.webc",
  });

  return {
    dir: {
      input: "src",
      output: "_site",
    },
    passthroughFileCopy: true,
    templateFormats: ["html", "md", "webc"],
    htmlTemplateEngine: "webc",
    dataTemplateEngine: "webc",
    markdownTemplateEngine: "webc",
  };
};
