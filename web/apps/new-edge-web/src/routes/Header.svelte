<script>
	import { page } from "$app/stores";
</script>

<header>
	<a href="#content">Skip to content</a>
	<div class="corner">
		<a href="https://kit.svelte.dev">SvelteKit</a>
	</div>

	<nav>
		<ul>
			<li aria-current={$page.url.pathname === "/" ? "page" : undefined}>
				<a href="/">Home</a>
			</li>
			<li aria-current={$page.url.pathname === "/about" ? "page" : undefined}>
				<a href="/about">About</a>
			</li>
			<li aria-current={$page.url.pathname === "/blog" ? "page" : undefined}>
				<a href="/blog">Blog</a>
			</li>
			<li aria-current={$page.url.pathname.startsWith("/sverdle") ? "page" : undefined}>
				<a href="/sverdle">Sverdle</a>
			</li>
		</ul>
	</nav>

	<div>
		<a href="https://github.com/sveltejs/kit">GitHub</a>
	</div>
</header>
