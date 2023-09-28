module.exports = function (config) {
	config.addPassthroughCopy("src/assets")
	return {
		dir: {
			input: "src",
		},
		htmlTemplateEngine: "liquid",
		markdownTemplateEngine: "liquid",
		templateFormats: ["html", "md", "liquid"],
	};
};
