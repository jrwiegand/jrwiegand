const vite = require("@11ty/eleventy-plugin-vite");
const webc = require("@11ty/eleventy-plugin-webc");

module.exports = function (config) {
  /**
   * Using the WebC plugin
   * Documentation: https://www.11ty.dev/docs/languages/webc/
   */
  config.addPlugin(webc, {
    components: "**/*.webc",
  });

  /**
   * Using the Vite plugin
   * Documentation: https://www.11ty.dev/docs/server-vite/
   */
  config.addPlugin(vite, {});

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
