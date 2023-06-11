const vite = require("@11ty/eleventy-plugin-vite");
const webc = require("@11ty/eleventy-plugin-webc");

module.exports = function (config) {
  config.addPlugin(webc, {
    // Glob to find no-import global components
    // (The default changed from `false` in Eleventy WebC v0.7.0)
    components: "**/*.webc",

    // Adds an Eleventy WebC transform to process all HTML output
    useTransform: false,

    // Additional global data used in the Eleventy WebC transform
    transformData: {},

    // Options passed to @11ty/eleventy-plugin-bundle
    bundlePluginOptions: {},
  });

  config.addPlugin(vite);

  config.setServerOptions({
    // Default values are shown:

    // Whether the live reload snippet is used
    liveReload: true,

    // Whether DOM diffing updates are applied where possible instead of page reloads
    domDiff: true,

    // The starting port number
    // Will increment up to (configurable) 10 times if a port is already in use.
    port: 8080,

    // Additional files to watch that will trigger server updates
    // Accepts an Array of file paths or globs (passed to `chokidar.watch`).
    // Works great with a separate bundler writing files to your output folder.
    // e.g. `watch: ["_site/**/*.css"]`
    watch: [],

    // Show local network IP addresses for device testing
    showAllHosts: false,

    // Use a local key/certificate to opt-in to local HTTP/2 with https
    https: {
      // key: "./localhost.key",
      // cert: "./localhost.cert",
    },

    // Change the default file encoding for reading/serving files
    encoding: "utf-8",
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
