<slot />

<footer>
	<p>
		<a
			style="text-decoration: none"
			href="https://hynek.me/til/copyright-years/"
			target="_blank"
			rel="noopener noreferrer">©</a
		>
		1989 Josh Wiegand
	</p>
</footer>

<style>
	@font-face {
		font-family: 'IBM Plex Mono';
		font-style: normal;
		font-weight: 400;
		font-display: swap;
		src:
			local('IBM Plex Mono'),
			url('/ibm-plex-mono-regular.woff2') format('woff2');
	}

	/* Box sizing rules */
	:global(*, *::before, *::after) {
		box-sizing: border-box;
	}

	/* Prevent font size inflation */
	:global(html) {
		-moz-text-size-adjust: none;
		-webkit-text-size-adjust: none;
		text-size-adjust: none;
	}

	/* Remove default margin in favour of better control in authored CSS */
	:global(body, h1, h2, h3, h4, p, figure, blockquote, dl, dd) {
		margin-block-end: 0;
	}

	/* Remove list styles on ul, ol elements with a list role, which suggests default styling will be removed */
	:global(ul[role='list'], ol[role='list']) {
		list-style: none;
	}

	/* Set core body defaults */
	:global(body) {
		min-height: 100vh;
		line-height: 1.5;
	}

	/* Set shorter line heights on headings and interactive elements */
	:global(h1, h2, h3, h4, button, input, label) {
		line-height: 1.1;
	}

	/* Balance text wrapping on headings */
	:global(h1, h2, h3, h4) {
		text-wrap: balance;
	}

	/* A elements that don't have a class get default styles */
	:global(a:not([class])) {
		text-decoration-skip-ink: auto;
		color: currentColor;
	}

	/* Make images easier to work with */
	:global(img, picture) {
		max-width: 100%;
		display: block;
	}

	/* Inherit fonts for inputs and buttons */
	:global(input, button, textarea, select) {
		font: inherit;
	}

	/* Make sure textareas without a rows attribute are not tiny */
	:global(textarea:not([rows])) {
		min-height: 10em;
	}

	/* Anything that has been anchored to should have extra scroll margin */
	:target {
		scroll-margin-block: 5ex;
	}

	:global(body) {
		font-family: IBM Plex Mono, monospace;
		padding: 5vh 10vw;
		line-height: 1.6;
		font-size: 1.2rem;
		display: grid;
		grid-template-rows: auto 1fr auto;
		min-height: 100vh;
	}

	footer {
		max-width: 32rem;
	}
</style>
