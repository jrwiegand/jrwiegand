<script lang="ts">
	import { navigating } from "$app/stores";

	let previous: { from: { url: URL }, to: { url: URL } };
	let start: number;
	let end: number;

	$: if ($navigating) {
		start = Date.now();
		end = 0;
		previous = $navigating;
	} else {
		end = Date.now();
	}
</script>
<nav>
	<a href="/">home</a>
	<a href="/slow-a" data-sveltekit-preload-data>/slow-a</a>
	<a href="/slow-b">/slow-b</a>
</nav>

<slot />

{#if previous && end}
	<p>navigated from {previous.from.url.pathname} to {previous.to.url.pathname} in <strong>{end - start}ms</strong></p>
{/if}