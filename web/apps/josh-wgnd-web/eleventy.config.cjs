module.exports = function () {
	return {
		dir: {
			input: "src",
		},
		htmlTemplateEngine: "liquid",
		markdownTemplateEngine: "liquid",
		templateFormats: ["html", "md", "liquid"],
	};
};
